package com.main.crs.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "car_variant")
@Getter
@Setter
public class CarVariant {
	
	@Id
	private String registration;
	
	@Column(name = "owner_name")
	private String ownerName;
	
	@Column(name = "owner_phone")
	private String ownerPhone;
	
	@Column(name = "owner_address")
	private String ownerAddress;
	
	@Column(name = "insurance_validity")
	private Date insuranceValidity;
	
	@Column(name = "puc_validity")
	private Date pucValidity;
	
	@Column(name = "model_color")
	private String modelColor;
	private String status;
	
	@ManyToOne
	@JoinColumn(name = "model_id")
	private Car car;

}
