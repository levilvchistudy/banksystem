package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "transaction_log", catalog = "banksystem")
public class TransactionLog implements java.io.Serializable {

	// Fields

	private Integer id;
	private Account accountByOtherid;
	private TransactionType transactionType;
	private Account accountByAccountid;
	private Double trMoney;
	private String datetime;




	public TransactionLog() {
	}

	public TransactionLog(Account accountByOtherid,
			TransactionType transactionType, Account accountByAccountid,
			Double trMoney, String datetime) {
		this.accountByOtherid = accountByOtherid;
		this.transactionType = transactionType;
		this.accountByAccountid = accountByAccountid;
		this.trMoney = trMoney;
		this.datetime = datetime;
	}


	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "otherid")
	public Account getAccountByOtherid() {
		return this.accountByOtherid;
	}

	public void setAccountByOtherid(Account accountByOtherid) {
		this.accountByOtherid = accountByOtherid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ta_type")
	public TransactionType getTransactionType() {
		return this.transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "accountid")
	public Account getAccountByAccountid() {
		return this.accountByAccountid;
	}

	public void setAccountByAccountid(Account accountByAccountid) {
		this.accountByAccountid = accountByAccountid;
	}

	@Column(name = "tr_money", precision = 18)
	public Double getTrMoney() {
		return this.trMoney;
	}

	public void setTrMoney(Double trMoney) {
		this.trMoney = trMoney;
	}

	@Column(name = "datetime", length = 50)
	public String getDatetime() {
		return this.datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

}