package entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@SuppressWarnings("serial")
@Entity
@Table(name = "transaction_type", catalog = "banksystem")
public class TransactionType implements java.io.Serializable {

	private Integer id;
	private String name;
	private Set<TransactionLog> transactionLogs = new HashSet<TransactionLog>(0);

	public TransactionType() {
	}

	public TransactionType(String name, Set<TransactionLog> transactionLogs) {
		this.name = name;
		this.transactionLogs = transactionLogs;
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

	@Column(name = "name", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "transactionType")
	public Set<TransactionLog> getTransactionLogs() {
		return this.transactionLogs;
	}

	public void setTransactionLogs(Set<TransactionLog> transactionLogs) {
		this.transactionLogs = transactionLogs;
	}

}