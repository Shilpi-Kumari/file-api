package cloud.springbootstarter.user;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


public interface userRepository extends CrudRepository<userEntity, String>{

	public List<userEntity> findByEmailid(String emailid);
}
