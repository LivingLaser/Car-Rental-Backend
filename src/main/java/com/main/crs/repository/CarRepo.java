package com.main.crs.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.main.crs.entity.Car;

public interface CarRepo extends JpaRepository<Car, Integer> {
	
	@Query(value = Queries.RANDOM_CARS, nativeQuery = true)
	List<Car> findByRandom();
	Page<Car> findByModelNameContaining(String keyword, Pageable pageable);
	
	@Query(value = Queries.FILTERED_CARS)
	Page<Car> findByFilter(@Param("mileage") String mileage, @Param("seatCapacity") String seatCapacity, @Param("bootSpace") String bootSpace, @Param("rentPrice") String rentPrice, Pageable pageable);
	
	@Query(value = Queries.MAX_MILEAGE, nativeQuery = true)
	Integer findMaxMileage();
	
	@Query(value = Queries.MIN_MILEAGE, nativeQuery = true)
	Integer findMinMileage();
	
	@Query(value = Queries.MAX_SEAT_CAPACITY, nativeQuery = true)
	Integer findMaxSeatCapacity();
	
	@Query(value = Queries.MIN_SEAT_CAPACITY, nativeQuery = true)
	Integer findMinSeatCapacity();
	
	@Query(value = Queries.MAX_BOOT_SPACE, nativeQuery = true)
	Integer findMaxBootSpace();
	
	@Query(value = Queries.MIN_BOOT_SPACE, nativeQuery = true)
	Integer findMinBootSpace();
	
	@Query(value = Queries.MAX_RENT_PRICE, nativeQuery = true)
	Integer findMaxRentPrice();
	
	@Query(value = Queries.MIN_RENT_PRICE, nativeQuery = true)
	Integer findMinRentPrice();

}
