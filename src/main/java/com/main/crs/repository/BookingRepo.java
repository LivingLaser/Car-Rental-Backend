package com.main.crs.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.main.crs.entity.Booking;
import com.main.crs.entity.User;

public interface BookingRepo extends JpaRepository<Booking, String> {
	
	Page<Booking> findByUserOrderByBookingDateTimeDesc(User user, Pageable pageable);
	List<Booking> findByBookingStatusOrderByBookingDateTime(String bookingStatus);
	Page<Booking> findAllByOrderByBookingDateTimeDesc(Pageable pageable);

}
