package com.main.crs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.main.crs.config.AppConstants;
import com.main.crs.entity.Role;
import com.main.crs.repository.RoleRepo;

@SpringBootApplication
public class CarRentalBackendApplication implements CommandLineRunner {
	
	@Autowired
	private RoleRepo roleRepo;

	public static void main(String[] args) {
		SpringApplication.run(CarRentalBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Role adminRole = new Role(AppConstants.USER_ADMIN, "ROLE_ADMIN");
		Role employeeRole = new Role(AppConstants.USER_EMPLOYEE, "ROLE_EMPLOYEE");
		Role userRole = new Role(AppConstants.USER_NORMAL, "ROLE_USER");
		List<Role> roles = List.of(adminRole, employeeRole, userRole);
		roleRepo.saveAll(roles);
	}

}
