package com.main.crs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.main.crs.config.AppConstants;
import com.main.crs.dto.UserDto;
import com.main.crs.payload.UserResponse;
import com.main.crs.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/crs/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserDto userDto) {
		UserDto createdUser = userService.registerUser(userDto);
		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	}
	
	@GetMapping("/login")
	public ResponseEntity<UserDto> loginUser(@RequestParam String email, @RequestParam String password) {
		UserDto loggedUser = userService.loginUser(email, password);
		return new ResponseEntity<>(loggedUser, HttpStatus.OK);
	}
	
	@PostMapping("/emp_register")
	public ResponseEntity<UserDto> registerEmployee(@Valid @RequestBody UserDto userDto) {
		UserDto createdEmployee = userService.registerEmployee(userDto);
		return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
	}
	
	@GetMapping("/emp_login")
	public ResponseEntity<UserDto> loginEmployee(@RequestParam String email, @RequestParam String password) {
		UserDto loggedEmployee = userService.loginEmployee(email, password);
		return new ResponseEntity<>(loggedEmployee, HttpStatus.OK);
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
	
	@GetMapping("/")
	public ResponseEntity<UserResponse> getAllUsers(
			@RequestParam(defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(defaultValue = AppConstants.SORT_BY, required = false) String sortBy) {
		UserResponse userResponse = userService.getAllUsers(pageNumber, pageSize, sortBy);
		return new ResponseEntity<>(userResponse, HttpStatus.OK);
	}
	
	@GetMapping("/emp")
	public ResponseEntity<UserResponse> getAllEmployees(
			@RequestParam(defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(defaultValue = AppConstants.SORT_BY, required = false) String sortBy) {
		UserResponse userResponse = userService.getAllEmployees(pageNumber, pageSize, sortBy);
		return new ResponseEntity<>(userResponse, HttpStatus.OK);
	}

}
