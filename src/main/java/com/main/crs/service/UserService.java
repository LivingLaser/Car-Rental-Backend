package com.main.crs.service;

import com.main.crs.dto.UserDto;
import com.main.crs.payload.UserResponse;

public interface UserService {
	
	UserDto registerUser(UserDto userDto);
	UserDto loginUser(String email, String password);
	UserDto registerOwner(UserDto userDto);
	UserDto loginOwner(String email, String password);
	UserDto loginAdmin(String email, String password);
	UserDto getUserById(Integer userId);
	UserDto updateProfile(UserDto userDto, Integer userId);
	UserDto changePassword(String email, String password, String newPassword);
	UserResponse getAllUsers(Integer pageNumber, Integer pageSize, String sortBy);
	UserResponse getAllOwners(Integer pageNumber, Integer pageSize, String sortBy);

}
