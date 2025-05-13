package com.main.crs.controller;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.main.crs.config.AppConstants;
import com.main.crs.dto.UserDto;
import com.main.crs.payload.UserResponse;
import com.main.crs.service.FileService;
import com.main.crs.service.UserService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/crs/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private FileService fileService;
	
	@PostMapping("/register")
	public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserDto userDto) {
		UserDto createdUser = userService.registerUser(userDto);
		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<UserDto> loginUser(@RequestParam String email, @RequestParam String password) {
		UserDto loggedUser = userService.loginUser(email, password);
		return new ResponseEntity<>(loggedUser, HttpStatus.OK);
	}
	
	@PostMapping("/emp_register")
	public ResponseEntity<UserDto> registerOwner(@Valid @RequestBody UserDto userDto) {
		UserDto createdEmployee = userService.registerOwner(userDto);
		return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
	}
	
	@PostMapping("/emp_login")
	public ResponseEntity<UserDto> loginOwner(@RequestParam String email, @RequestParam String password) {
		UserDto loggedEmployee = userService.loginOwner(email, password);
		return new ResponseEntity<>(loggedEmployee, HttpStatus.OK);
	}
	
	@PostMapping("/admin_login")
	public ResponseEntity<UserDto> loginAdmin(@RequestParam String email, @RequestParam String password) {
		UserDto loggedAdmin = userService.loginAdmin(email, password);
		return new ResponseEntity<>(loggedAdmin, HttpStatus.OK);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUserById(@PathVariable Integer userId) {
		UserDto userDto = userService.getUserById(userId);
		return new ResponseEntity<>(userDto, HttpStatus.OK);
	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateProfile(@Valid @RequestBody UserDto userDto, @PathVariable Integer userId) {
		UserDto updatedUser = userService.updateProfile(userDto, userId);
		return new ResponseEntity<>(updatedUser, HttpStatus.OK);
	}
	
	@PutMapping("/change_password")
	public ResponseEntity<UserDto> changePassword(@RequestParam String email, @RequestParam String password, @RequestParam String newPassword) {
		UserDto updatedUser = userService.changePassword(email, password, newPassword);
		return new ResponseEntity<>(updatedUser, HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<UserResponse> getAllUsers(
			@RequestParam(defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(defaultValue = AppConstants.USER_SORT_BY, required = false) String sortBy) {
		UserResponse userResponse = userService.getAllUsers(pageNumber, pageSize, sortBy);
		return new ResponseEntity<>(userResponse, HttpStatus.OK);
	}
	
	@GetMapping("/emp")
	public ResponseEntity<UserResponse> getAllOwners(
			@RequestParam(defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(defaultValue = AppConstants.USER_SORT_BY, required = false) String sortBy) {
		UserResponse userResponse = userService.getAllOwners(pageNumber, pageSize, sortBy);
		return new ResponseEntity<>(userResponse, HttpStatus.OK);
	}
	
	@PostMapping("/upload/image/{userId}")
	public ResponseEntity<UserDto> uploadImage(@PathVariable Integer userId, @RequestParam MultipartFile image) throws IOException {
		UserDto userDto = userService.getUserById(userId);
		String fileName = fileService.uploadImage(AppConstants.USER_IMAGE_PATH, image);
		userDto.setUserImage(fileName);
		UserDto updateUser = userService.updateProfile(userDto, userId);
		return new ResponseEntity<>(updateUser, HttpStatus.OK);
	}
	
	@GetMapping(value = "/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void getImageResource(@PathVariable String imageName, HttpServletResponse response) throws IOException {
		InputStream imageResource = fileService.getImageResource(AppConstants.USER_IMAGE_PATH, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(imageResource, response.getOutputStream());
	}

}
