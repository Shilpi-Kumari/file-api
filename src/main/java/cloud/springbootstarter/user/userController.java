package cloud.springbootstarter.user;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cloud.springbootstarter.file.FileEntity;


@RestController
public class userController {
	
	@Autowired
	private userService uService;
	
	@RequestMapping("/getall")
	public List<userEntity> getall(){
		return uService.getall();
	}
	
	@RequestMapping("/login")	
	public Map<String, String> login(@RequestParam String emailid,@RequestParam String password){
		return uService.logincheck(emailid,password);
	}
	
	@RequestMapping("/social/login")	
	public Map<String, String> socialLogin(@RequestParam String emailid, @RequestParam String fname, @RequestParam String lname){
		return uService.socialLoginData(emailid, fname, lname);
	}
	
	
	@RequestMapping(value="/addUser", method = RequestMethod.POST)	
	public Map<String, String> addUser(@RequestParam String firstname,@RequestParam String lastname,@RequestParam String emailid,@RequestParam String password) throws IOException {
		String id = UUID.randomUUID().toString().replace("-", "");
		return uService.addUser(id,firstname,lastname,emailid,password);
	}

}
