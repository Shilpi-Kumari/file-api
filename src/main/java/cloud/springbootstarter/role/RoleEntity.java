package cloud.springbootstarter.role;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="role")
public class RoleEntity {
	
	@Id
	@Column(name = "account_id")
	private String accountid;
	private String emailid;
	private String role;
	
	public RoleEntity() {
	}
	
	public RoleEntity(String accountid, String emailid, String role) {
		super();
		this.accountid = accountid;
		this.emailid = emailid;
		this.role = role;
	}
	
	public String getAccount_id() {
		return accountid;
	}
	public void setAccount_id(String accountid) {
		this.accountid = accountid;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	

}
