package com.main.crs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.main.crs.entity.Car;
import com.main.crs.entity.CarVariant;
import com.main.crs.entity.User;

public interface CarVariantRepo extends JpaRepository<CarVariant, String> {
	
	List<CarVariant> findByCar(Car car);
	List<CarVariant> findByUser(User user);
	
	@Query(value = Queries.CLEAR_VARIANTS)
	List<CarVariant> clearVariants();

}
