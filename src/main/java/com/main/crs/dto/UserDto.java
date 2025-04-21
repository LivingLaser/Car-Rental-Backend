package com.main.crs.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
	
	private Integer userId;
	
	@NotBlank(message = "Name must not be empty")
	@Pattern(regexp = "^[A-Za-z\\s]+$", message = "Enter a valid name")
	private String name;
	
	@NotBlank(message = "Email must not be empty")
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@(gmail|yahoo|outlook)\\.com$", message = "Enter a valid email")
	private String email;
	
	@NotBlank(message = "Phone number must not be empty")
	@Pattern(regexp = "[6-9]{1}[0-9]{9}", message = "Enter a valid phone number")
	private String phone;
	
	@NotBlank(message = "Address must not be empty")
	@Pattern(regexp = "^(?!.*<[^>]+>).*$", message = "Enter a valid address")
	private String address;
	
	@NotBlank(message = "Pincode must not be empty")
	@Pattern(regexp = "[1-9]{1}[0-9]{5}", message = "Enter a valid pincode")
	private String pincode;
	
	@NotBlank(message = "Password must not be empty")
	@Size(min = 6, max = 13, message = "Password must be between 6-13 characters")
	private String password;
	private RoleDto role;
	
	@JsonIgnore
	public String getPassword() {
		return password;
	}
	
	@JsonProperty
	public void setPassword(String password) {
		this.password = password;
	}

}
