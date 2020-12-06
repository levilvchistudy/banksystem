package action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import biz.AdminBiz;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.Account;
import entity.Admin;

@SuppressWarnings({"serial","unchecked","rawtypes"})
public class AdminAction extends ActionSupport{

	private static final String PAGE = "page";
	private static final int NUM = 16;
	private static final String ADMIN_RESET = "adminreset";
	private static final String ADMIN_INFO = "admininfo";
	protected static final String ADD = "add";


	private AdminBiz adminBiz;

	private int page = 0;
	private String newpass;
	private String renewpass;
	private String mpass;

	private String realname;
	private int age;
	private String sex;
	private long cardid;
	private String address;
	private String telephone;
	private String username;
	private String password;
	
	public String add(){
		return exec(new ExecAd() {
			public String exec(Object v) {
				String msg = SUCCESS;
				if(isNull(realname,sex,address,telephone,username,password)){
					return ADD;
				}else if(age>18&&age<99&&(sex.equals("男")||sex.equals("女"))
						&&(cardid+"").matches("[0-9]{1,18}")&&telephone.matches("1[0-9]{1,10}")){
					if(password.length()<5){
						msg = info(password+"该密码过于简单");
					}else if(adminBiz.hasAccount(username)){
						msg = info(username+"用户已存在");
					}else{
						List<String> list = adminBiz.add(realname,age,sex,cardid,address,telephone,username,password);
						if(list==null||list.isEmpty()){
							msg =  info("开户失败,数据库发生插入错误");
						}else{
							ServletActionContext.getRequest().setAttribute("msg", "开户成功");
							ServletActionContext.getRequest().setAttribute("info", list);
						}
					}
				}else{
					msg =  info("有不符合规则的参数");
				}
				realname=sex=address=telephone=username=password = null;
				age = (int) (cardid = 0L);
				return msg;
			}
		});
	}

	boolean isNull(String...s){
		for(int i = 0;i<s.length;i++){
			if(s[i]==null||s[i].trim().equals("")){
				return true;
			}
		}
		return false;
	}

	public String page(){
		return exec(new ExecAd<Object>() {
			public String exec(Object t) {
				
				page = adminBiz.pageNo(page,NUM);
				
				int[] numList = adminBiz.accountNumList(page,NUM);

				List<Account> list = adminBiz.accountList(page,NUM);
				ServletActionContext.getRequest().setAttribute("type", "page");
				return selectRquest(page,numList,list);
			}
		});
	}

	public String open(){
		return exec(new ExecAd<Object>(){
			public String exec(Object t) {
				page = adminBiz.pageOpenNo(page,NUM);
				int[] numList = adminBiz.accountOpenNumList(page,NUM);
				List<Account> list = adminBiz.accountOpenList(page,NUM);
				ServletActionContext.getRequest().setAttribute("type", "open");
				return selectRquest(page, numList, list);
			}			
		});
	}

	public String freeze(){
		return exec(new ExecAd<Object>(){
			public String exec(Object t) {
				page = adminBiz.pageFreezeNo(page,NUM);
				int[] numList = adminBiz.accountFreezeNumList(page,NUM);
				List<Account> list = adminBiz.accountFreezeList(page,NUM);
				ServletActionContext.getRequest().setAttribute("type", "freeze");
				return selectRquest(page, numList, list);
			}			
		});
	}

	private String selectRquest(int page,int[] numList,List list){
		if(list!=null&&!list.isEmpty()){
			ServletActionContext.getRequest().setAttribute("text", list);
			ServletActionContext.getRequest().setAttribute("no", numList);
			ServletActionContext.getRequest().setAttribute("now", page);
			page = 0;
			return PAGE;
		}
		return info("没有相关数据!");
	}

	public String reset(){
		return exec(new ExecAd<Admin>(){
			public String exec(Admin admin) {
				if(isNull(mpass,renewpass,newpass)||(renewpass.length()<5||
						!renewpass.equals(newpass))||!mpass.equals(admin.getPassword())
						||!adminBiz.reset(admin,newpass)){
					return ADMIN_RESET;
				}
				return info("修改密码成功");
			}
		});
	}

	public String info(String info){
		ServletActionContext.getRequest().setAttribute("info", info);
		return ADMIN_INFO;
	}


	public String exit(){
		ActionContext.getContext().getSession().clear();
		return NONE;
	}


	private String exec(ExecAd e){
		Object o = ActionContext.getContext().getSession().get("admin");
		if(o!=null&&o instanceof Admin){
			String msg =  e.exec(o);
			page = 0;
			return msg;
		}else{
			return LOGIN;
		}
	}	

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getNewpass() {
		return newpass;
	}

	public void setNewpass(String newpass) {
		this.newpass = newpass;
	}

	public String getRenewpass() {
		return renewpass;
	}

	public void setRenewpass(String renewpass) {
		this.renewpass = renewpass;
	}

	public String getMpass() {
		return mpass;
	}

	public void setMpass(String mpass) {
		this.mpass = mpass;
	}

	public AdminBiz getAdminBiz() {
		return adminBiz;
	}

	public void setAdminBiz(AdminBiz adminBiz) {
		this.adminBiz = adminBiz;
	}

	interface ExecAd<V> extends Exec<String,V> {
		String exec(V v);
	}

	public String getRealname() {
		return realname;
	}



	public void setRealname(String realname) {
		this.realname = realname;
	}



	public int getAge() {
		return age;
	}



	public void setAge(int age) {
		this.age = age;
	}



	public String getSex() {
		return sex;
	}



	public void setSex(String sex) {
		this.sex = sex;
	}



	public long getCarDid() {
		return cardid;
	}



	public void setCarDid(long carid) {
		this.cardid = carid;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public String getTelephone() {
		return telephone;
	}



	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}


}
