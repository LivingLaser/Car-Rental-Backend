package com.main.crs.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.main.crs.entity.Booking;
import com.main.crs.entity.User;

public interface BookingRepo extends JpaRepository<Booking, String> {
	
	Page<Booking> findByUserOrderByBookingDateTimeDesc(User user, Pageable pageable);
	List<Booking> findByBookingStatusOrderByBookingDateTime(String bookingStatus);
	Page<Booking> findAllByOrderByBookingDateTimeDesc(Pageable pageable);
	
	@Query(value = Queries.TOTAL_REVENUE, nativeQuery = true)
	String totalRevenue();
	
	@Query(value = Queries.BOOKING_COUNT)
	String totalBookings();
	
	@Query(value = Queries.BOOKING_STATUS_COUNT)
	String countByBookingStatus(@Param("bookingStatus") String bookingStatus);

}
