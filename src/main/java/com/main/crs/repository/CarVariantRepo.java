package com.main.crs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.main.crs.entity.Car;
import com.main.crs.entity.CarVariant;

public interface CarVariantRepo extends JpaRepository<CarVariant, String> {
	
	List<CarVariant> findByCar(Car car);
	
	@Query(value = Queries.CLEAR_VARIANTS)
	List<CarVariant> clearVariants();

}
