package cloud.springbootstarter.file;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileController {
	
	@Autowired
	private FileService fileService;
	//private String uploadtime;
	//private String id;
	
	/*@RequestMapping("/files")
	public FileEntity getFiles() {
		return fileService.saveFile("test_file", "how are you");
	}*/
	
	@RequestMapping(value="accounts/{account_id}/files/upload", method = RequestMethod.POST)
	public Map<String, String> uploadFiles(@RequestParam MultipartFile user_file, @RequestParam String description, @PathVariable String account_id) throws IOException {
		Map<String, String> result = new HashMap<String, String>();
		if(user_file.isEmpty()) {
			result.put("status", "false");
			result.put("reason", "No File Uploaded");
			return result;
		}
		if(user_file.getSize() > 10485760) {
			result.put("status", "false");
			result.put("reason", "Upload is not successfull.Please upload File which is less than 10 MB.");
			return result;
		}
		fileService.uploadFile(user_file, account_id);
		String name = user_file.getOriginalFilename();
		Date date=new Date();
		String uploadtime=date.toString();
		String id = UUID.randomUUID().toString().replace("-", "");
		fileService.saveFile(id,account_id,name,description,uploadtime,uploadtime);
		result.put("status", "true");
		result.put("reason", "File Uploaded Successfully..!!");
		return result;
	}
	
	@RequestMapping(value="accounts/{account_id}/files/{file_id}", method = RequestMethod.DELETE)
	public Boolean updateFiles(@PathVariable String account_id, @PathVariable String file_id) throws IOException {
		return fileService.deleteFile(account_id, file_id);
	}
	
	@RequestMapping(value="admin/accounts/{account_id}/files/{file_id}", method = RequestMethod.DELETE)
	public Boolean deleteAdminFile(@PathVariable String account_id, @PathVariable String file_id) throws IOException {
		return fileService.deleteAdminFile(account_id, file_id);
	}
	@RequestMapping("accounts/{account_id}/files")
	public List<FileEntity> getFiles(@PathVariable String account_id) {
		return fileService.getAllFiles(account_id);
	}
	
	@RequestMapping("admin/{account_id}/listfiles")
	public List<FileEntity> adminGetFiles(@PathVariable String account_id) {
		return fileService.getAdminAllFiles(account_id);
	}
	
}
