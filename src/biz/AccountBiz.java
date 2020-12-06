package biz;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;

import dao.AccountDAO;
import dao.PersoninfoDAO;
import dao.TransactionLogDAO;
import dao.TransactionTypeDAO;
import entity.Account;
import entity.Personinfo;
import entity.TransactionLog;
import entity.TransactionType;

public class AccountBiz {

	private AccountDAO accountDAO;
	private PersoninfoDAO personinfoDAO;
	private TransactionLogDAO trasDAO;
	private TransactionTypeDAO transactionTypeDAO;

	public void setAccountDAO(AccountDAO accountDAO) {
		this.accountDAO = accountDAO;
	}
	public void setPersoninfoDAO(PersoninfoDAO personinfoDAO) {
		this.personinfoDAO = personinfoDAO;
	}
	public void setTrasDAO(TransactionLogDAO trasDAO) {
		this.trasDAO = trasDAO;
	}
	public void setTransactionTypeDAO(TransactionTypeDAO transactionTypeDAO) {
		this.transactionTypeDAO = transactionTypeDAO;
	}	
	
	private TransactionType getType(int index){
		return transactionTypeDAO.findById(index+1);
	}
	
	public Account login(Account account){
		List<?> list = null;
		if((list = accountDAO.findByExample(account)).size()==1){
			return (Account) list.get(0);
		}
		return null;
	}

	public List<String> getPersonInfo(Account account) {
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

	public boolean diposit(Account account, int cout) {
		cout = -cout;
		return withdraw(account,cout);
	}

	public boolean withdraw(Account account, int cout) {
		if(account.getBalance()<cout)
			return false;
		account.setBalance(account.getBalance()-cout);
		this.accountDAO.attachDirty(account);
		ActionContext.getContext().getSession().put("account", account);
		return true;
	}

	public boolean reset(Account account, String newpass) {
		try{
			account.setPassword(newpass);
			this.accountDAO.attachDirty(account);
			ActionContext.getContext().getSession().put("account", account);
		}catch(RuntimeException re){
			return false;
		}
		return true;
	}
	
	public List<TransactionLog> record(Account account) {
		List<TransactionLog> list = trasDAO.findByAccount(account);
		if(list!=null)
			Collections.reverse(list);
		return list;
	}
	
	public boolean tras(Account account, int cout, int otherid) {
		Account other = accountDAO.findById(otherid);
		if(other!=null&&account.getBalance()>cout){
			String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			TransactionLog t1 = new TransactionLog(account,getType(0),other,(double) cout,date);
			TransactionLog t2 = new TransactionLog(other,getType(1),account,(double) cout,date);
			account.setBalance(account.getBalance()-cout);
			other.setBalance(other.getBalance()+cout);
			trasDAO.merge(t1);
			trasDAO.merge(t2);
			accountDAO.attachDirty(account);
			accountDAO.attachDirty(other);
			return true;
		}
		return false;
	}
}
