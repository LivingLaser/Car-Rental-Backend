package com.main.crs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.main.crs.config.AppConstants;
import com.main.crs.entity.Role;
import com.main.crs.entity.User;
import com.main.crs.repository.RoleRepo;
import com.main.crs.repository.UserRepo;

@SpringBootApplication
public class CarRentalBackendApplication implements CommandLineRunner {
	
	@Autowired
	private RoleRepo roleRepo;
	
	@Autowired
	private UserRepo userRepo;

	public static void main(String[] args) {
		SpringApplication.run(CarRentalBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Role adminRole = new Role(AppConstants.USER_ADMIN, "ROLE_ADMIN");
		Role ownerRole = new Role(AppConstants.USER_OWNER, "ROLE_OWNER");
		Role userRole = new Role(AppConstants.USER_NORMAL, "ROLE_USER");
		List<Role> roles = List.of(adminRole, ownerRole, userRole);
		roleRepo.saveAll(roles);
		
		User admin = new User();
		admin.setUserId(1);
		admin.setName("ADMIN");
		admin.setEmail("admin@crs.com");
		admin.setPassword("nimda");
		admin.setRole(adminRole);
		userRepo.save(admin);
	}

}
