package com.main.crs.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.main.crs.entity.Role;
import com.main.crs.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {
	
	User findByEmailAndPasswordAndRole(String email, String password, Role role);
	Page<User> findByRole(Role role, Pageable pageable);
	
	@Query(value = Queries.USER_COUNT)
	String countUsersByRole(@Param("roleId") Integer roleId);

}
