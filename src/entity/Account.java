package entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@SuppressWarnings("serial")
@Entity
@Table(name = "account", catalog = "banksystem")
public class Account implements java.io.Serializable {

	// Fields

	private Integer accountid;
	private Status status;
	private String username;
	private String password;
	private Double balance;
	private Set<TransactionLog> transactionLogsForOtherid = new HashSet<TransactionLog>(
			0);
	private Set<TransactionLog> transactionLogsForAccountid = new HashSet<TransactionLog>(
			0);
	private Set<Personinfo> personinfos = new HashSet<Personinfo>(0);

	
	public Account() {
	}

	public Account(Status status, String username, String password,
			Double balance, Set<TransactionLog> transactionLogsForOtherid,
			Set<TransactionLog> transactionLogsForAccountid,
			Set<Personinfo> personinfos) {
		this.status = status;
		this.username = username;
		this.password = password;
		this.balance = balance;
		this.transactionLogsForOtherid = transactionLogsForOtherid;
		this.transactionLogsForAccountid = transactionLogsForAccountid;
		this.personinfos = personinfos;
	}

	public Account(Status type, String username, String password, int balance) {
		this.status = type;
		this.username = username;
		this.password = password;
		this.balance = (double) balance;
	}

	
	@Id
	@GeneratedValue
	@Column(name = "accountid", unique = true, nullable = false)
	public Integer getAccountid() {
		return this.accountid;
	}

	public void setAccountid(Integer accountid) {
		this.accountid = accountid;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "status")
	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Column(name = "username", length = 50)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password", length = 50)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "balance", precision = 18)
	public Double getBalance() {
		return this.balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "accountByOtherid")
	public Set<TransactionLog> getTransactionLogsForOtherid() {
		return this.transactionLogsForOtherid;
	}

	public void setTransactionLogsForOtherid(
			Set<TransactionLog> transactionLogsForOtherid) {
		this.transactionLogsForOtherid = transactionLogsForOtherid;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "accountByAccountid")
	public Set<TransactionLog> getTransactionLogsForAccountid() {
		return this.transactionLogsForAccountid;
	}

	public void setTransactionLogsForAccountid(
			Set<TransactionLog> transactionLogsForAccountid) {
		this.transactionLogsForAccountid = transactionLogsForAccountid;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "account")
	public Set<Personinfo> getPersoninfos() {
		return this.personinfos;
	}

	public void setPersoninfos(Set<Personinfo> personinfos) {
		this.personinfos = personinfos;
	}

}