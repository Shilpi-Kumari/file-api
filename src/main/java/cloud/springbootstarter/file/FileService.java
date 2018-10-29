package cloud.springbootstarter.file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;

import cloud.springbootstarter.role.RoleEntity;
import cloud.springbootstarter.role.RoleRepository;
import cloud.springbootstarter.user.userEntity;
import cloud.springbootstarter.user.userRepository;


@Service
public class FileService {
	
	@Autowired
	private Environment env;
	
	@Autowired
	private AmazonS3 s3client;
	
	@Autowired
	private FileRepository fileRepository;
	
	@Autowired
	private userRepository uRepository;
	
	@Autowired
	private RoleRepository rRepository;
	
	public void uploadFile(MultipartFile file, String account_id) throws IOException {
		File tempFile = File.createTempFile("temp_file_to_upload", "suffix");
		try {
			file.transferTo(tempFile);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String key = account_id + "/" + file.getOriginalFilename();
		s3client.putObject(env.getProperty("bucket"), key, tempFile);
	}
	
	public Boolean saveFile(String id,String account_id,String name,String description,String uploadtime,String updatetime) {
		List<FileEntity> files = fileRepository.findByAccountidAndName(account_id, name);
		if(files.size()==0) {
			FileEntity fe = new FileEntity(id,account_id,name,description,uploadtime,updatetime);
			fileRepository.save(fe);
		}else
		{
			FileEntity fe = files.get(0);
			fe.setUpdatetime(uploadtime);
			fe.setDescription(description);
			fileRepository.save(fe);
		}
		return true;
	}
	
	public Boolean deleteFile(String account_id, String file_id) {
		List<FileEntity> files = fileRepository.findByAccountidAndId(account_id, file_id);
		System.out.println(files.size());
		if(files.size()==0) {
			return false;
		}
		FileEntity fileEntity = files.get(0);
		String fileName = fileEntity.getName();
		String key = account_id + "/" + fileName;
		s3client.deleteObject(env.getProperty("bucket"), key);
		fileRepository.deleteById(file_id);
		return true;
	}
	
	public Boolean deleteAdminFile(String account_id,String file_id) {
		Optional<FileEntity> option= fileRepository.findById(file_id);
		//System.out.println(files.size());
		if(!option.isPresent()){
			return false;
		}
		Optional<RoleEntity> option1 = rRepository.findById(account_id);
		if(!option1.isPresent()){
			return false;
		}
		else {
			RoleEntity role = option1.get();
			if (!role.getRole().equals("admin")) {
				return false;
			}
		}
		FileEntity fileEntity = option.get();
		String fileName = fileEntity.getName();
		String key = account_id + "/" + fileName;
		s3client.deleteObject(env.getProperty("bucket"), key);
		fileRepository.deleteById(file_id);
		return true;
	}
	
	public List<FileEntity> getAllFiles(String account_id) {
		List<FileEntity> fileList = new ArrayList<>();
		String prefix = env.getProperty("cloudfrontUrl") + "/" + account_id + "/";
		fileRepository.findByAccountid(account_id).forEach(fileList::add);
		for (FileEntity fileEntity : fileList) {
			fileEntity.setCf_url(prefix + fileEntity.getName());
		}
		return fileList;
	}
	
	public List<FileEntity> getAdminAllFiles(String account_id) {
		List<FileEntity> fileList = new ArrayList<>();
		Optional<RoleEntity> option = rRepository.findById(account_id);
		if(!option.isPresent())
		{
			return fileList;
		}
		else {
			RoleEntity role = option.get();
			if (!role.getRole().equals("admin")) {
				return fileList;
			}
			//String prefix = env.getProperty("cloudfrontUrl") + "/" + account_id + "/";
			String prefix = env.getProperty("cloudfrontUrl");
			fileRepository.findAll().forEach(fileList::add);
			for (FileEntity fileEntity : fileList) {
				fileEntity.setCf_url(prefix +"/"+ fileEntity.getAccount_id()+"/"+fileEntity.getName());
				String email_id = uRepository.findById(fileEntity.getAccount_id()).get().getEmailid();
				fileEntity.setAccount_id(email_id);
			}
			return fileList;
		}
	}

}
