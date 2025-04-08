package com.main.crs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.crs.entity.Role;

public interface RoleRepo extends JpaRepository<Role, Integer> {

}
