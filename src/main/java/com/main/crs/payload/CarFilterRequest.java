package com.main.crs.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
