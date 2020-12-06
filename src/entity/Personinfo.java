package entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@SuppressWarnings("serial")
@Entity
@Table(name = "personinfo", catalog = "banksystem")
public class Personinfo implements java.io.Serializable {

	// Fields

	private PersoninfoId id;
	private Account account;
	private String realname;
	private Integer age;
	private String sex;
	private Long cardid;
	private String address;
	private String telephone;

	// Constructors


	public Personinfo() {
	}


	public Personinfo(PersoninfoId id, Account account) {
		this.id = id;
		this.account = account;
	}

	public Personinfo(PersoninfoId id, Account account, String realname,
			Integer age, String sex, Long cardid, String address,
			String telephone) {
		this.id = id;
		this.account = account;
		this.realname = realname;
		this.age = age;
		this.sex = sex;
		this.cardid = cardid;
		this.address = address;
		this.telephone = telephone;
	}

	
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "id", column = @Column(name = "id", nullable = false)),
			@AttributeOverride(name = "accountid", column = @Column(name = "accountid", nullable = false)) })
	public PersoninfoId getId() {
		return this.id;
	}

	public void setId(PersoninfoId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "accountid", nullable = false, insertable = false, updatable = false)
	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Column(name = "realname", length = 50)
	public String getRealname() {
		return this.realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	@Column(name = "age")
	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Column(name = "sex", length = 2)
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "cardid", precision = 18, scale = 0)
	public Long getCardid() {
		return this.cardid;
	}

	public void setCardid(Long cardid) {
		this.cardid = cardid;
	}

	@Column(name = "address", length = 50)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "telephone", length = 50)
	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

}