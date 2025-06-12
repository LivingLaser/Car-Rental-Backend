package com.main.crs.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.main.crs.config.AppConstants;
import com.main.crs.dto.BookingDto;
import com.main.crs.entity.Booking;
import com.main.crs.entity.Car;
import com.main.crs.entity.CarVariant;
import com.main.crs.entity.User;
import com.main.crs.exception.ResourceNotFoundException;
import com.main.crs.payload.AdminDashboard;
import com.main.crs.payload.BookingResponse;
import com.main.crs.repository.BookingRepo;
import com.main.crs.repository.CarRepo;
import com.main.crs.repository.CarVariantRepo;
import com.main.crs.repository.UserRepo;
import com.main.crs.service.BookingService;

@Service
public class BookingServiceImpl implements BookingService {
	
	@Autowired
	private BookingRepo bookingRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CarRepo carRepo;
	
	@Autowired
	private CarVariantRepo carVariantRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public BookingDto userBooking(BookingDto bookingDto, Integer userId, Integer modelId) {
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "ID", userId));
		Car car = carRepo.findById(modelId).orElseThrow(() -> new ResourceNotFoundException("Car", "Model ID", modelId));
		Booking booking = modelMapper.map(bookingDto, Booking.class);
		booking.setUser(user);
		booking.setCar(car);
		Booking newBooking = bookingRepo.save(booking);
		return modelMapper.map(newBooking, BookingDto.class);
	}

	@Override
	public BookingDto confirmBooking(String bookingId, String registration) {
		CarVariant carVariant = carVariantRepo.findById(registration).orElseThrow(() -> new ResourceNotFoundException("Vehicle", registration + " Registration", 404));
		carVariant.setStatus("Booked");
		CarVariant bookedVariant = carVariantRepo.save(carVariant);
		Booking booking = bookingRepo.findById(bookingId).orElseThrow(() -> new ResourceNotFoundException("Booking", bookingId + " Booking ID", 404));
		booking.setCarVariant(bookedVariant);
		booking.setBookingStatus("Confirmed");
		Booking confirmedBooking = bookingRepo.save(booking);
		return modelMapper.map(confirmedBooking, BookingDto.class);
	}

	@Override
	public BookingDto updateBooking(String bookingId, String bookingStatus) {
		Booking booking = bookingRepo.findById(bookingId).orElseThrow(() -> new ResourceNotFoundException("Booking", bookingId + " Booking ID", 404));
		booking.setBookingStatus(bookingStatus);
		Booking updatedBooking = bookingRepo.save(booking);
		return modelMapper.map(updatedBooking, BookingDto.class);
	}

	@Override
	public BookingResponse getBookingsByUser(Integer userId, Integer pageNumber, Integer pageSize) {
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "ID", userId));
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Booking> pages = bookingRepo.findByUserOrderByBookingDateTimeDesc(user, pageable);
		
		List<Booking> bookings = pages.getContent();
		List<BookingDto> bookingDtos = bookings.stream().map(booking -> modelMapper.map(booking, BookingDto.class)).collect(Collectors.toList());
		
		BookingResponse bookingResponse = new BookingResponse();
		bookingResponse.setPageContent(bookingDtos);
		bookingResponse.setPageNumber(pages.getNumber());
		bookingResponse.setTotalPages(pages.getTotalPages());
		
		return bookingResponse;
	}

	@Override
	public List<BookingDto> getBookingsByStatus(String bookingStatus) {
		List<Booking> bookings = bookingRepo.findByBookingStatusOrderByBookingDateTime(bookingStatus);
		List<BookingDto> bookingDtos = bookings.stream().map(booking -> modelMapper.map(booking, BookingDto.class)).collect(Collectors.toList());
		return bookingDtos;
	}

	@Override
	public BookingResponse getAllBookings(Integer pageNumber, Integer pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Booking> pages = bookingRepo.findAllByOrderByBookingDateTimeDesc(pageable);
		
		List<Booking> bookings = pages.getContent();
		List<BookingDto> bookingDtos = bookings.stream().map(booking -> modelMapper.map(booking, BookingDto.class)).collect(Collectors.toList());
		
		BookingResponse bookingResponse = new BookingResponse();
		bookingResponse.setPageContent(bookingDtos);
		bookingResponse.setPageNumber(pages.getNumber());
		bookingResponse.setTotalPages(pages.getTotalPages());
		
		return bookingResponse;
	}

	@Override
	public AdminDashboard getDashboardData() {
		AdminDashboard adminDashboard = new AdminDashboard();
		adminDashboard.setTotalUsers(userRepo.countUsersByRole(AppConstants.USER_NORMAL));
		adminDashboard.setTotalOwners(userRepo.countUsersByRole(AppConstants.USER_OWNER));
		adminDashboard.setTotalRevenue(bookingRepo.totalRevenue());
		adminDashboard.setTotalBookings(bookingRepo.totalBookings());
		adminDashboard.setActiveCars(carVariantRepo.countVariantsByStatus("Active"));
		adminDashboard.setBookedCars(carVariantRepo.countVariantsByStatus("Booked"));
		adminDashboard.setConfirmedBookings(bookingRepo.countByBookingStatus("Confirmed"));
		adminDashboard.setCanceledBookings(bookingRepo.countByBookingStatus("Canceled"));
		adminDashboard.setDeclinedBookings(bookingRepo.countByBookingStatus("Declined"));
		return adminDashboard;
	}

}
