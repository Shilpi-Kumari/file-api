package cloud.springbootstarter.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cloud.springbootstarter.file.FileEntity;
import cloud.springbootstarter.file.FileRepository;
import cloud.springbootstarter.role.RoleEntity;
import cloud.springbootstarter.role.RoleRepository;

@Service
public class userService {
	
	@Autowired
	private userRepository uRepository;
	
	@Autowired
	private RoleRepository rRepository;
	
	public List<userEntity> getall() {
		List<userEntity> u = new ArrayList<>();
		uRepository.findAll()
		.forEach(u::add);
		return u;
		
	}
	
	public Map<String, String> addUser(String id,String firstname,String lastname,String emailid,String password){
		
		List<userEntity> ue = uRepository.findByEmailid(emailid);
		HashMap<String, String> map = new HashMap<>();
		if(ue.size()!=0) {
			map.put("status", "false");
			map.put("reason", "Email Id already registered");
		} else {
			userEntity uen = new userEntity(id,firstname,lastname,emailid,password);
			uRepository.save(uen);
			map.put("status", "true");
		}
		return map;
	}
	
	public Map<String, String> socialLoginData(String emailid, String fname, String lname) {
		List<userEntity> ue = uRepository.findByEmailid(emailid);
		HashMap<String, String> map = new HashMap<>();
		if(ue.size()==0) {
			String id = UUID.randomUUID().toString().replace("-", "");
			userEntity uen = new userEntity(id,fname,lname,emailid,"social");
			uRepository.save(uen);
			
			map.put("account_id", id);
			map.put("fname", fname);
			map.put("lname", lname);
			map.put("emailid", emailid);
		}
		else {
			userEntity loggedInUser = ue.get(0);
			map.put("account_id", loggedInUser.getId());
			map.put("fname", loggedInUser.getFirstname());
			map.put("lname", loggedInUser.getLastname());
			map.put("emailid", loggedInUser.getEmailid());
		}
		map.put("status", "true");
		map.put("role", "user");
		return map;
	}

	public Map<String, String> logincheck(String emailid, String password) {
		List<userEntity> ue = uRepository.findByEmailid(emailid);
		HashMap<String, String> map = new HashMap<>();
		if(ue.size()==0) {
			map.put("status", "false");
			map.put("reason", "User does not exist");
		}
		else {
			userEntity loggedInUser = ue.get(0);
			if(password.equals(loggedInUser.getPassword())){
				String role = "user";
				Optional<RoleEntity> option = rRepository.findById(loggedInUser.getId());
				if(option.isPresent() && option.get().getRole().equals("admin")) {
					role="admin";
				}
				map.put("status", "true");
				map.put("account_id", loggedInUser.getId());
				map.put("fname", loggedInUser.getFirstname());
				map.put("lname", loggedInUser.getLastname());
				map.put("emailid", loggedInUser.getEmailid());
				map.put("role", role);
			}
			else {
				map.put("status", "false");
				map.put("reason", "Email/Password does not match");
				
			}
		}
		return map;
	}
	
}
