package com.main.crs.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarFilterRequest {
	
	private Integer maxMileage;
	private Integer minMileage;
	private Integer maxSeatCapacity;
	private Integer minSeatCapacity;
	private Integer maxBootSpace;
	private Integer minBootSpace;
	private Integer maxRentPrice;
	private Integer minRentPrice;

}
