package com.main.crs.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
	
	@Column(name = "insurance_validity")
	private Date insuranceValidity;
	
	@Column(name = "puc_validity")
	private Date pucValidity;
	
	@Column(name = "model_color")
	private String modelColor;
	private String status;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "model_id")
	private Car car;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;
	
	@OneToMany(mappedBy = "carVariant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Booking> bookings;

}
