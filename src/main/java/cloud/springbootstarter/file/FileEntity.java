package cloud.springbootstarter.file;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity(name="files")
public class FileEntity {
	
	@Id
	private String id;
	
	@Column(name = "account_id")
	private String accountid;
	private String description;
	private String name;
	private String uploadtime;
	private String updatetime;
	
	@Transient
	private String cf_url;
	
	public FileEntity() {
		
	}
	
	
	public FileEntity(String id, String account_id,String name , String description, String uploadtime, String updatetime) {
		super();
		this.id = id;
		this.accountid = account_id;
		this.description = description;
		this.name = name;
		this.uploadtime = uploadtime;
		this.updatetime = updatetime;
	}




	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getAccount_id() {
		return accountid;
	}


	public void setAccount_id(String account_id) {
		this.accountid = account_id;
	}


	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	public String getUploadtime() {
		return uploadtime;
	}

	public void setUploadtime(String uploadtime) {
		this.uploadtime = uploadtime;
	}
	
	public String getCf_url() {
		return cf_url;
	}

	public void setCf_url(String cf_url) {
		this.cf_url = cf_url;
	}
	

}
