package com.main.crs.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.main.crs.entity.Car;

public interface CarRepo extends JpaRepository<Car, Integer> {
	
	@Query(value = "select * from car order by RAND() limit 6", nativeQuery = true)
	List<Car> findByRandom();
	Page<Car> findByModelNameContaining(String keyword, Pageable pageable);
	
	@Query(value = "SELECT c FROM Car c WHERE c.mileage <= :mileage AND c.seatCapacity <= :seatCapacity AND c.bootSpace <= :bootSpace AND c.rentPrice <= :rentPrice")
	Page<Car> findByFilter(@Param("mileage") String mileage, @Param("seatCapacity") String seatCapacity, @Param("bootSpace") String bootSpace, @Param("rentPrice") String rentPrice, Pageable pageable);

}
