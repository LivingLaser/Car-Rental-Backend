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
	
	@NotBlank(message = "Enter Owner Name")
	@Pattern(regexp = "^[A-Za-z\\s]+$", message = "Enter Valid Name")
	private String ownerName;
	
	@NotBlank(message = "Enter Owner Phone No.")
	@Pattern(regexp = "[6-9]{1}[0-9]{9}", message = "Enter Valid Phone No.")
	private String ownerPhone;
	
	@NotBlank(message = "Enter Address")
	@Pattern(regexp = "^(?!.*<[^>]+>).*$", message = "Enter Valid Address")
	private String ownerAddress;
	
	@NotNull(message = "Enter Insurance Validity")
	private Date insuranceValidity;
	
	@NotNull(message = "Enter PUC Validity")
	private Date pucValidity;
	
	@NotBlank(message = "Select a Color")
	private String modelColor;
	
	@Builder.Default
	private String status = "Active";

}
