package com.main.crs.repository;

public interface Queries {
	
	String RANDOM_CARS = "select * from car order by RAND() limit 6";
	String FILTERED_CARS = "SELECT c FROM Car c WHERE c.mileage <= :mileage AND c.seatCapacity <= :seatCapacity AND c.bootSpace <= :bootSpace AND c.rentPrice <= :rentPrice";
	String MAX_MILEAGE = "select IFNULL(MAX(CAST(mileage as UNSIGNED)), 0) from car";
	String MIN_MILEAGE = "select IFNULL(MIN(CAST(mileage as UNSIGNED)), 0) from car";
	String MAX_SEAT_CAPACITY = "select IFNULL(MAX(CAST(seat_capacity as UNSIGNED)), 0) from car";
	String MIN_SEAT_CAPACITY = "select IFNULL(MIN(CAST(seat_capacity as UNSIGNED)), 0) from car";
	String MAX_BOOT_SPACE = "select IFNULL(MAX(CAST(boot_space as UNSIGNED)), 0) from car";
	String MIN_BOOT_SPACE = "select IFNULL(MIN(CAST(boot_space as UNSIGNED)), 0) from car";
	String MAX_RENT_PRICE = "select IFNULL(MAX(CAST(rent_price as UNSIGNED)), 0) from car";
	String MIN_RENT_PRICE = "select IFNULL(MIN(CAST(rent_price as UNSIGNED)), 0) from car";

}
