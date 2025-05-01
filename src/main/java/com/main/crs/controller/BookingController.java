package com.main.crs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.main.crs.config.AppConstants;
import com.main.crs.dto.BookingDto;
import com.main.crs.payload.BookingResponse;
import com.main.crs.service.BookingService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/crs/bookings")
public class BookingController {
	
	@Autowired
	BookingService bookingService;
	
	@PostMapping("/user/{userId}/car/{modelId}")
	public ResponseEntity<BookingDto> userBooking(@Valid @RequestBody BookingDto bookingDto, @PathVariable Integer userId, @PathVariable Integer modelId) {
		BookingDto newBooking = bookingService.userBooking(bookingDto, userId, modelId);
		return new ResponseEntity<>(newBooking, HttpStatus.CREATED);
	}
	
	@PostMapping("/{bookingId}/variant/{registration}")
	public ResponseEntity<BookingDto> confirmBooking(@PathVariable String bookingId, @PathVariable String registration) {
		BookingDto confirmedBooking = bookingService.confirmBooking(bookingId, registration);
		return new ResponseEntity<>(confirmedBooking, HttpStatus.OK);
	}
	
	@PutMapping("/{bookingId}/set_status/{bookingStatus}")
	public ResponseEntity<BookingDto> updateBooking(@PathVariable String bookingId, @PathVariable String bookingStatus) {
		BookingDto updatedBooking = bookingService.updateBooking(bookingId, bookingStatus);
		return new ResponseEntity<>(updatedBooking, HttpStatus.OK);
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<BookingResponse> getBookingsByUser(@PathVariable Integer userId,
			@RequestParam(defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize) {
		BookingResponse bookingResponse = bookingService.getBookingsByUser(userId, pageNumber, pageSize);
		return new ResponseEntity<>(bookingResponse, HttpStatus.OK);
	}
	
	@GetMapping("/status/{bookingStatus}")
	public ResponseEntity<List<BookingDto>> getBookingsByStatus(@PathVariable String bookingStatus) {
		List<BookingDto> bookingDtos = bookingService.getBookingsByStatus(bookingStatus);
		return new ResponseEntity<>(bookingDtos, HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<BookingResponse> getAllBookings(
			@RequestParam(defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize) {
		BookingResponse bookingResponse = bookingService.getAllBookings(pageNumber, pageSize);
		return new ResponseEntity<>(bookingResponse, HttpStatus.OK);
	}

}
