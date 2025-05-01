package com.main.crs.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Booking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "booking_id")
	private String bookingId;
	
	@Column(name = "booking_date_time")
	private Date bookingDateTime;
	
	@Column(name = "pick_date")
	private Date pickDate;
	
	@Column(name = "drop_date")
	private Date dropDate;
	private String amount;
	
	@Column(name = "booking_status")
	private String bookingStatus;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "model_id")
	private Car car;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "registration")
	private CarVariant carVariant;

}
