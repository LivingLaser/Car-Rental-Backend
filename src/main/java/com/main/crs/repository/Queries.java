package com.main.crs.repository;

public interface Queries {
	
	String RANDOM_CARS = "select * from car order by RAND() limit 6";
	String FILTERED_CARS = "select * from car where CAST(mileage as UNSIGNED) <= :mileage AND CAST(seat_capacity as UNSIGNED) <= :seatCapacity AND CAST(boot_space as UNSIGNED) <= :bootSpace AND CAST(rent_price as UNSIGNED) <= :rentPrice";
	String MAX_MILEAGE = "select IFNULL(MAX(CAST(mileage as UNSIGNED)), 0) from car";
	String MIN_MILEAGE = "select IFNULL(MIN(CAST(mileage as UNSIGNED)), 0) from car";
	String MAX_SEAT_CAPACITY = "select IFNULL(MAX(CAST(seat_capacity as UNSIGNED)), 0) from car";
	String MIN_SEAT_CAPACITY = "select IFNULL(MIN(CAST(seat_capacity as UNSIGNED)), 0) from car";
	String MAX_BOOT_SPACE = "select IFNULL(MAX(CAST(boot_space as UNSIGNED)), 0) from car";
	String MIN_BOOT_SPACE = "select IFNULL(MIN(CAST(boot_space as UNSIGNED)), 0) from car";
	String MAX_RENT_PRICE = "select IFNULL(MAX(CAST(rent_price as UNSIGNED)), 0) from car";
	String MIN_RENT_PRICE = "select IFNULL(MIN(CAST(rent_price as UNSIGNED)), 0) from car";
	String CLEAR_VARIANTS = "SELECT c FROM CarVariant c JOIN c.bookings b WHERE c.status = 'Booked' AND b.dropDate < CURRENT_TIMESTAMP";

}
