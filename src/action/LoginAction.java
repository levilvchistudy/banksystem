package action;

import biz.AccountBiz;
import biz.AdminBiz;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.Account;
import entity.Admin;

@SuppressWarnings("serial")
public class LoginAction extends ActionSupport{
	public static final String ACCOUNT = "account";
	public static final String ADMIN = "admin";
	
	private AccountBiz accountBiz;
	private AdminBiz adminBiz;
	private String username;
	private String password;
	private String login;
	
	@Override
	public String execute() throws Exception {
		if(isNull(login,username,password)){
			return LOGIN;
		}else if (login.equals("account")) {
			Account account = new Account();
			account.setUsername(username);
			account.setPassword(password);
			if((account = accountBiz.login(account))!=null){
				ActionContext.getContext().getSession().put("account", account);
				return ACCOUNT;
			}
		}else if(login.equals("admin")){
			Admin admin = new Admin();
			admin.setPassword(password);
			admin.setPassword(password);
			if((admin = adminBiz.login(admin))!=null){
				ActionContext.getContext().getSession().put("admin", admin);
				return ADMIN;
			}
		}
		return LOGIN;
	}
	
	boolean isNull(String...s){
		for(int i = 0;i<s.length;i++){
			if(s[i]==null||s[i].trim().equals("")){
				return true;
			}
		}
		return false;
	}
	
	public AccountBiz getAccountBiz() {
		return accountBiz;
	}
	public void setAccountBiz(AccountBiz accountBiz) {
		this.accountBiz = accountBiz;
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
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public void setAdminBiz(AdminBiz adminBiz) {
		this.adminBiz = adminBiz;
	}
	public AdminBiz getAdminBiz() {
		return adminBiz;
	}
}
