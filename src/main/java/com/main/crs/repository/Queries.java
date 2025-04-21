package com.main.crs.repository;

public interface Queries {
	
	String RANDOM_CARS = "select * from car order by RAND() limit 6";
	String FILTERED_CARS = "SELECT c FROM Car c WHERE c.mileage <= :mileage AND c.seatCapacity <= :seatCapacity AND c.bootSpace <= :bootSpace AND c.rentPrice <= :rentPrice";

}
