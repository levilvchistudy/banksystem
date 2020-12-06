package biz;

import java.util.ArrayList;
import java.util.List;

import action.Exec;

import com.opensymphony.xwork2.ActionContext;

import entity.Account;
import entity.Personinfo;
import entity.PersoninfoId;
import entity.Status;
import dao.AccountDAO;
import dao.AdminDAO;
import dao.PersoninfoDAO;
import dao.StatusDAO;
import entity.Admin;

public class AdminBiz {
	private static final String AND = "and";
	private AdminDAO adminDAO;
	private AccountDAO accountDAO;
	private StatusDAO statusDAO;
	private PersoninfoDAO personinfoDAO;
	
	private int max;
	private int size;

	public Admin login(Admin admin){
		List<?> list = null;
		return (list = adminDAO.findByExample(admin)).size()==1?((Admin) list.get(0)):null;
	}

	public AdminDAO getAdminDAO() {
		return adminDAO;
	}

	public void setAdminDAO(AdminDAO adminDAO) {
		this.adminDAO = adminDAO;
	}

	public int pageNo(int page, int num) {
		return pageNo(accountDAO.count(),page,num);
	}
	private int pageNo(int size,int page,int num){
		this.size = size;
		max = (size-1)/num+1;
		page = page<1?1:(page>max?max:page);
		return page;
	}

	public List<Account> accountList(int page, int num) {
		return accountList(page,num,new Exec<List<Account>,String>(){
			public List<Account> exec(String v) {
				return accountDAO.findWithSection(Integer.parseInt(v.split(AND)[0]),Integer.parseInt(v.split(AND)[1]));
			}
		});
	}
	
	private List<Account> accountList(int page,int num,Exec<List<Account>,String> e){
		page = (page>0)?((page>max)?max:page):1;
		int left = (page-1)*num;
		int right = page*num;
		right = right>=size?size:right;
		return e.exec(left+AND+right);
	}

	public int[] accountNumList(int page, int num) {
		num = num>=max?max:num;
		int left = 0,right = 0;
		if(page<=num/2){
			left = 1;
			right = num;
		}else if(page+num/2>=max){
			left = max-num;
			right = max;
		}else{
			left = page - num/2;
			right = page + num/2;
		}
		left = left==0?1:left;
		int n[] = new int[right-left+1];
		for (int i = 0; i < n.length; i++) {
			n[i] = i + left;			
		}
		return n;
	}

	public AccountDAO getAccountDAO() {
		return accountDAO;
	}

	public void setAccountDAO(AccountDAO accountDAO) {
		this.accountDAO = accountDAO;
	}

	public boolean reset(Admin admin, String newpass) {
		try{
			admin.setPassword(newpass);
			adminDAO.attachDirty(admin);
			ActionContext.getContext().getSession().put("admin", admin);
		}catch(RuntimeException re){
			return false;
		}
		return true;
	}

	public int pageOpenNo(int page, int num) {
		return pageNo(accountDAO.count("status",getType(0)),page,num);
	}

	public int[] accountOpenNumList(int page, int num) {
		return accountNumList(page, num);
	}

	public List<Account> accountOpenList(int page, int num) {
		return accountList(page, num, 0);
	}
	
	private List<Account> accountList(int page,int num,final int type){
		return accountList(page, num, new Exec<List<Account>,String>(){
			public List<Account> exec(String v) {
				return accountDAO.findWithSection(Integer.parseInt(v.split(AND)[0]),
						Integer.parseInt(v.split(AND)[1]),"status",getType(type));
			}
		});
	}

	public int pageFreezeNo(int page, int num) {
		return pageNo(accountDAO.count("status",getType(1)),page,num);
	}

	public int[] accountFreezeNumList(int page, int num) {
		return accountNumList(page, num);
	}

	public List<Account> accountFreezeList(int page, int num) {
		return accountList(page, num, 1);
	}

	public StatusDAO getStatusDAO() {
		return statusDAO;
	}

	public void setStatusDAO(StatusDAO statusDAO) {
		this.statusDAO = statusDAO;
	}
	public Status getType(int index){
		return statusDAO.findById(index+1);
	}

	public boolean hasAccount(String username) {
		return accountDAO.findByUsername(username).size()>0;
	}

	public List<String> add(String realname, int age, String sex, long cardid,
			String address, String telephone, String username, String password) {
		Account account = new Account(getType(0), username, password, 0);
		accountDAO.attachDirty(account);
		List<?> list = accountDAO.findByExample(account);
		if(list.size()!=1)
			return null;
		account = (Account) list.get(0);
		Personinfo personinfo = new Personinfo();
		personinfo.setId(new PersoninfoId(account.getAccountid(),account.getAccountid()));
		personinfo.setAccount(account);
		personinfo.setAddress(address);
		personinfo.setAge(age);
		personinfo.setCardid(cardid);
		personinfo.setRealname(realname);
		personinfo.setTelephone(telephone);
		personinfo.setSex(sex);
		personinfoDAO.attachDirty(personinfo);
		list = personinfoDAO.findByAccount(account);
		if(list.size()==0)
			return null;
		return getPersonInfo(account);
	}
	
	private List<String> getPersonInfo(Account account) {
		Personinfo p = personinfoDAO.findByAccount(account).get(0);
		if(p!=null){
			List<String> info = new ArrayList<>(8);
			info.add("账户:"+p.getAccount().getAccountid()+"");
			info.add("存款:"+p.getAccount().getBalance()+"");
			info.add("姓名:"+p.getRealname());
			info.add("年龄:"+p.getAge()+"");
			info.add("身份证:"+p.getCardid()+"");
			info.add("电话号码:"+p.getTelephone()+"");
			return info;
		}
		return null;
	}

	public PersoninfoDAO getPersoninfoDAO() {
		return personinfoDAO;
	}

	public void setPersoninfoDAO(PersoninfoDAO personinfoDAO) {
		this.personinfoDAO = personinfoDAO;
	}
	
	
}
