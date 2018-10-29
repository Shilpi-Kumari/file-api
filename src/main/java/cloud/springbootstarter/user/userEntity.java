package cloud.springbootstarter.user;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity(name="users")
public class userEntity {
	
	@Id
	private String id;
	private String firstname;
	private String lastname;
	private String emailid;
	private String password;
	
	public userEntity() {
		
	}

	public userEntity(String id, String firstname, String lastname, String emailid, String password) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.emailid = emailid;
		this.password = password;
	}

	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
