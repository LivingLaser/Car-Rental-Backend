package com.main.crs.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Car {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "model_id")
	private Integer modelId;
	
	@Column(name = "model_name")
	private String modelName;
	private String mileage;
	private String engine;
	private String transmission;
	
	@Column(name = "seat_capacity")
	private String seatCapacity;
	
	@Column(name = "boot_space")
	private String bootSpace;
	
	@Column(name = "fuel_type")
	private String fuelType;
	
	@Column(name = "fuel_capacity")
	private String fuelCapacity;
	
	@Column(name = "fuel_unit")
	private String fuelUnit;
	
	@Column(name = "rent_price")
	private String rentPrice;
	
	@Column(name = "model_image")
	private String modelImage;
	
	@OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
	private List<CarVariant> carVariants;

}
