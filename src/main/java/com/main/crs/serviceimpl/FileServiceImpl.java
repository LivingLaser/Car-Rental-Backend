package com.main.crs.serviceimpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.main.crs.service.FileService;

@Service
public class FileServiceImpl implements FileService {

	@Override
	public String uploadImage(String path, MultipartFile multipartFile) throws IOException {
		File file = new File(path);
		
		if(!file.exists()) {
			file.mkdir();
		}
		
		String fileName = multipartFile.getOriginalFilename();
		String filePath = path + File.separator + fileName;
		Files.copy(multipartFile.getInputStream(), Paths.get(filePath));
		
		return fileName;
	}

	@Override
	public InputStream getImageResource(String path, String fileName) throws IOException {
		String filePath = path + File.separator + fileName;
		InputStream inputStream = new FileInputStream(filePath);
		return inputStream;
	}

}
