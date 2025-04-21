package com.main.crs.service;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	
	String uploadImage(String path, MultipartFile multipartFile) throws IOException;
	InputStream getImageResource(String path, String fileName) throws IOException;

}
