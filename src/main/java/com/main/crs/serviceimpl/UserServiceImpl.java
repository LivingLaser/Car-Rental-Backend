package com.main.crs.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.main.crs.config.AppConstants;
import com.main.crs.dto.UserDto;
import com.main.crs.entity.Role;
import com.main.crs.entity.User;
import com.main.crs.exception.ResourceNotFoundException;
import com.main.crs.payload.UserResponse;
import com.main.crs.repository.RoleRepo;
import com.main.crs.repository.UserRepo;
import com.main.crs.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private RoleRepo roleRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto registerUser(UserDto userDto) {
		Role role = roleRepo.findById(AppConstants.USER_NORMAL).orElse(null);
		User user = modelMapper.map(userDto, User.class);
		user.setRole(role);
		User savedUser = userRepo.save(user);
		return modelMapper.map(savedUser, UserDto.class);
	}

	@Override
	public UserDto loginUser(String email, String password) {
		Role role = roleRepo.findById(AppConstants.USER_NORMAL).orElse(null);
		User user = userRepo.findByEmailAndPasswordAndRole(email, password, role);
		return modelMapper.map(user, UserDto.class);
	}

	@Override
	public UserDto registerEmployee(UserDto userDto) {
		Role role = roleRepo.findById(AppConstants.USER_EMPLOYEE).orElse(null);
		User employee = modelMapper.map(userDto, User.class);
		employee.setRole(role);
		User savedEmployee = userRepo.save(employee);
		return modelMapper.map(savedEmployee, UserDto.class);
	}

	@Override
	public UserDto loginEmployee(String email, String password) {
		Role role = roleRepo.findById(AppConstants.USER_EMPLOYEE).orElse(null);
		User employee = userRepo.findByEmailAndPasswordAndRole(email, password, role);
		return modelMapper.map(employee, UserDto.class);
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "ID", userId));
		return modelMapper.map(user, UserDto.class);
	}

	@Override
	public UserDto updateProfile(UserDto userDto, Integer userId) {
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "ID", userId));
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPhone(userDto.getPhone());
		user.setAddress(userDto.getAddress());
		user.setPincode(userDto.getPincode());
		user.setPassword(userDto.getPassword());
		User updatedUser = userRepo.save(user);
		return modelMapper.map(updatedUser, UserDto.class);
	}

	@Override
	public UserResponse getAllUsers(Integer pageNumber, Integer pageSize, String sortBy) {
		Role role = roleRepo.findById(AppConstants.USER_NORMAL).orElse(null);
		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
		Page<User> pages = userRepo.findByRole(role, pageable);
		
		List<User> users = pages.getContent();
		List<UserDto> userDtos = users.stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
		
		UserResponse userResponse = new UserResponse();
		userResponse.setPageContent(userDtos);
		userResponse.setPageNumber(pages.getNumber());
		userResponse.setPageSize(pages.getSize());
		userResponse.setTotalPages(pages.getTotalPages());
		userResponse.setIsFirstPage(pages.isFirst());
		userResponse.setIsLastPage(pages.isLast());
		
		return userResponse;
	}

	@Override
	public UserResponse getAllEmployees(Integer pageNumber, Integer pageSize, String sortBy) {
		Role role = roleRepo.findById(AppConstants.USER_EMPLOYEE).orElse(null);
		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
		Page<User> pages = userRepo.findByRole(role, pageable);
		
		List<User> users = pages.getContent();
		List<UserDto> userDtos = users.stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
		
		UserResponse userResponse = new UserResponse();
		userResponse.setPageContent(userDtos);
		userResponse.setPageNumber(pages.getNumber());
		userResponse.setPageSize(pages.getSize());
		userResponse.setTotalPages(pages.getTotalPages());
		userResponse.setIsFirstPage(pages.isFirst());
		userResponse.setIsLastPage(pages.isLast());
		
		return userResponse;
	}

}
