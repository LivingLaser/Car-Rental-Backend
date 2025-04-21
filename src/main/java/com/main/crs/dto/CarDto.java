package com.main.crs.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarDto {
	
	private Integer modelId;
	
	@NotBlank(message = "Enter Model Name")
	private String modelName;
	
	@NotBlank(message = "Enter Mileage")
	@Pattern(regexp = "^\\d{1,3}+(\\.\\d{1,2})?$", message = "Enter Valid Mileage")
	private String mileage;
	
	@NotBlank(message = "Enter Engine Displacement [CC]")
	@Pattern(regexp = "^\\d{2,5}+(\\.\\d{1,2})?$", message = "Enter Valid Engine Displacement [CC]")
	private String engine;
	
	@NotBlank(message = "Select Transmission Mode")
	private String transmission;
	
	@NotBlank(message = "Select No. of Seats")
	private String seatCapacity;
	
	@NotBlank(message = "Enter Boot Space")
	@Pattern(regexp = "^\\d{1,4}+(\\.\\d{1,2})?$", message = "Enter Valid Boot Space")
	private String bootSpace;
	
	@NotBlank(message = "Select a Fuel type")
	private String fuelType;
	
	@NotBlank(message = "Enter Fuel Capacity")
	@Pattern(regexp = "^\\d{1,3}+(\\.\\d{1,2})?$", message = "Enter Valid Capacity")
	private String fuelCapacity;
	
	@NotBlank(message = "Select a measuring unit")
	private String fuelUnit;
	
	@NotBlank(message = "Enter Rent Value")
	@Pattern(regexp = "^\\d+(\\.\\d{1,2})?$", message = "Enter Valid Rent Value")
	private String rentPrice;
	
	@Builder.Default
	private String modelImage = "default.jpg";

}
