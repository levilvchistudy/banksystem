package entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@SuppressWarnings("serial")
@Embeddable
public class PersoninfoId implements java.io.Serializable {


	private Integer id;
	private Integer accountid;

	public PersoninfoId() {
	}

	public PersoninfoId(Integer id, Integer accountid) {
		this.id = id;
		this.accountid = accountid;
	}


	@Column(name = "id", nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "accountid", nullable = false)
	public Integer getAccountid() {
		return this.accountid;
	}

	public void setAccountid(Integer accountid) {
		this.accountid = accountid;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PersoninfoId))
			return false;
		PersoninfoId castOther = (PersoninfoId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getAccountid() == castOther.getAccountid()) || (this
						.getAccountid() != null
						&& castOther.getAccountid() != null && this
						.getAccountid().equals(castOther.getAccountid())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getAccountid() == null ? 0 : this.getAccountid().hashCode());
		return result;
	}

}