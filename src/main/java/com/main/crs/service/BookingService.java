package com.main.crs.service;

import java.util.List;

import com.main.crs.dto.BookingDto;
import com.main.crs.payload.BookingResponse;

public interface BookingService {
	
	BookingDto userBooking(BookingDto bookingDto, Integer userId, Integer modelId);
	BookingDto confirmBooking(String bookingId, String registration);
	BookingDto updateBooking(String bookingId, String bookingStatus);
	BookingResponse getBookingsByUser(Integer userId, Integer pageNumber, Integer pageSize);
	List<BookingDto> getBookingsByStatus(String bookingStatus);
	BookingResponse getAllBookings(Integer pageNumber, Integer pageSize);

}
