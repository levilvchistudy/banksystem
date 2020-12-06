package action;


import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import org.apache.struts2.ServletActionContext;

import biz.AccountBiz;
import entity.Account;
import entity.TransactionLog;

@SuppressWarnings("serial")
public class AccountAction extends ActionSupport{

	private static final String DEPOSIT = "deposit";
	private static final String WITHDRAW = "withdraw";
	private static final String INFO = "info";
	private static final String RESET = "reset";
	private static final String RECORD = "record";
	private static final String TRAS = "tras";

	private AccountBiz accountBiz;
	private int cout;
	private int otherid;	
	private String newpass;
	private String renewpass;
	private String mpass;

	public String info(){
		return info("");
	}

	private String info(final String msg){
		return exec(new ExecAc() {			
			public String exec(Account account) {
				List<String> info = accountBiz.getPersonInfo(account);
				if(info!=null){
					ServletActionContext.getRequest().setAttribute("info", info);
					ServletActionContext.getRequest().setAttribute("msg", msg);
					return INFO;
				}else
					return LOGIN;
			}
		});
	}

	public String reset(){
		return exec(new ExecAc(){
			public String exec(Account account) {
				if(mpass==null||renewpass==null||newpass==null||(renewpass.length()<5||
						!renewpass.equals(newpass))||!mpass.equals(account.getPassword())
						||!accountBiz.reset(account,newpass)){
					return RESET;
				}
				return info("修改密码成功!");
			}
		});
	}

	public String withdraw(){
		return opBalance(WITHDRAW, new ExecAc() {
			public String exec(Account account) {
				return accountBiz.withdraw(account, cout)?info("取款成功!"):WITHDRAW;
			}			
		});
	}

	public String deposit(){
		return opBalance(DEPOSIT, new ExecAc() {
			public String exec(Account account) {
				return accountBiz.diposit(account, cout)?info("存款成功!"):DEPOSIT;
			}			
		});
	}

	private String opBalance(final String none,final ExecAc e){
		return exec(new ExecAc() {
			public String exec(Account account) {
				if(cout!=0&&(cout+"").matches("[0-9]{1,8}")){
					String result = e.exec(account);
					cout = 0;
					return result;
				}else{
					return none;
				}
			}
		});
	}

	public String tras(){
		return opBalance(TRAS, new ExecAc() {
			public String exec(Account account) {
				if(otherid==0||!(otherid+"").matches("[0-9]{1,4}"))
					return TRAS;
				String msg =  accountBiz.tras(account,cout,otherid)?record():TRAS;
				otherid = 0;
				return msg;
			}
		});
	}

	
	public String record(){
		return exec(new ExecAc() {
			public String exec(Account account) {
				List<TransactionLog> list = accountBiz.record(account);
				ServletActionContext.getRequest().setAttribute("record", list);
				return RECORD;
			}
		});	
	}


	private String exec(ExecAc e){
		Account account;
		Object o = ActionContext.getContext().getSession().get("account");
		if(o!=null){
			account = (Account) o;
			String msg =  e.exec(account);
			return msg;
		}else{
			return LOGIN;
		}
	}	
	
	

	public String exit(){
		ActionContext.getContext().getSession().clear();
		return NONE;
	}

	public AccountBiz getAccountBiz() {
		return accountBiz;
	}

	public void setAccountBiz(AccountBiz accountBiz) {
		this.accountBiz = accountBiz;
	}

	public int getCout() {
		return cout;
	}

	public void setCout(int cout) {
		this.cout = cout;
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
	public int getOtherid() {
		return otherid;
	}
	public void setOtherid(int otherid) {
		this.otherid = otherid;
	}

	interface ExecAc extends Exec<String,Account>{
		String exec(Account account);
	}
}
