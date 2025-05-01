package com.main.crs.dto;

import java.util.Date;

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
	
	@NotNull(message = "Select Pickup Date")
	private Date pickDate;
	
	@NotNull(message = "Select Drop-off Date")
	private Date dropDate;
	
	@Pattern(regexp = "^\\d+(\\.\\d{1,2})?$", message = "Invalid Booking Amount")
	private String amount;
	
	@Builder.Default
	private String bookingStatus = "Pending Approval";
	private UserDto user;
	private CarDto car;
	private CarVariantDto carVariant;

}
