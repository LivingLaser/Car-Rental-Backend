package com.main.crs.dto;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class CarVariantDto {
	
	@NotBlank(message = "Enter Registration No.")
	@Pattern(regexp = "^[A-Z]{2}\\d{2}[A-Z]{1,2}\\d{4}$", message = "Enter Valid Registration No.")
	private String registration;
	
	@NotNull(message = "Enter Insurance Validity")
	private Date insuranceValidity;
	
	@NotNull(message = "Enter PUC Validity")
	private Date pucValidity;
	
	@NotBlank(message = "Select a Color")
	private String modelColor;
	
	@Builder.Default
	private String status = "Active";
	private CarDto car;
	private UserDto user;

}
