package com.main.crs.service;

import com.main.crs.dto.UserDto;
import com.main.crs.payload.UserResponse;

public interface UserService {
	
	UserDto registerUser(UserDto userDto);
	UserDto loginUser(String email, String password);
	UserDto registerEmployee(UserDto userDto);
	UserDto loginEmployee(String email, String password);
	UserDto getUserById(Integer userId);
	UserDto updateProfile(UserDto userDto, Integer userId);
	UserResponse getAllUsers(Integer pageNumber, Integer pageSize, String sortBy);
	UserResponse getAllEmployees(Integer pageNumber, Integer pageSize, String sortBy);

}
