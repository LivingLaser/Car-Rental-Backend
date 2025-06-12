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
public class BookingDto {
	
	private String bookingId;
	
	@Builder.Default
	private Date bookingDateTime = new Date();
	
	@NotBlank(message = "Enter Pickup Location")
	@Pattern(regexp = "^(?!.*<[^>]+>).*$", message = "Enter a Valid Pickup Location")
	private String pickLocation;
	
	@NotBlank(message = "Enter Drop-off Location")
	@Pattern(regexp = "^(?!.*<[^>]+>).*$", message = "Enter a Valid Drop-off Location")
	private String dropLocation;
	
	@NotNull(message = "Select Pickup Date")
	private Date pickDate;
	
	@NotNull(message = "Select Drop-off Date")
	private Date dropDate;
	
	@Pattern(regexp = "^\\d+(\\.\\d{1,2})?$", message = "Invalid Booking Amount")
	private String amount;
	
	@Builder.Default
	private String bookingStatus = "Pending";
	private CarDto car;
	private UserDto user;
	private CarVariantDto carVariant;

}
