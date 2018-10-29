package cloud.springbootstarter.file;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import cloud.springbootstarter.user.userEntity;

public interface FileRepository extends CrudRepository<FileEntity, String>{
	
	public List<FileEntity> findByAccountid(String account_id);
	
	public List<FileEntity> findByAccountidAndId(String account_id, String file_id);
	
	public List<FileEntity> findByAccountidAndName(String account_id, String name);

}
