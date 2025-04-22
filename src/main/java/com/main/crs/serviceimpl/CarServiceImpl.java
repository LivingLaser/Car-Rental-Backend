package com.main.crs.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.main.crs.dto.CarDto;
import com.main.crs.entity.Car;
import com.main.crs.exception.ResourceNotFoundException;
import com.main.crs.payload.CarFilterRequest;
import com.main.crs.payload.CarResponse;
import com.main.crs.repository.CarRepo;
import com.main.crs.service.CarService;

@Service
public class CarServiceImpl implements CarService {
	
	@Autowired
	private CarRepo carRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CarDto addCarModel(CarDto carDto) {
		Car car = modelMapper.map(carDto, Car.class);
		Car savedCar = carRepo.save(car);
		return modelMapper.map(savedCar, CarDto.class);
	}

	@Override
	public CarDto getModelById(Integer modelId) {
		Car car = carRepo.findById(modelId).orElseThrow(() -> new ResourceNotFoundException("Car", "Model ID", modelId));
		return modelMapper.map(car, CarDto.class);
	}

	@Override
	public CarDto updateModel(CarDto carDto, Integer modelId) {
		Car car = carRepo.findById(modelId).orElseThrow(() -> new ResourceNotFoundException("Car", "Model ID", modelId));
		car.setModelName(carDto.getModelName());
		car.setMileage(carDto.getMileage());
		car.setEngine(carDto.getEngine());
		car.setTransmission(carDto.getTransmission());
		car.setSeatCapacity(carDto.getSeatCapacity());
		car.setBootSpace(carDto.getBootSpace());
		car.setFuelType(carDto.getFuelType());
		car.setFuelCapacity(carDto.getFuelCapacity());
		car.setFuelUnit(carDto.getFuelUnit());
		car.setRentPrice(carDto.getRentPrice());
		car.setModelImage(carDto.getModelImage());
		Car updatedCar = carRepo.save(car);
		return modelMapper.map(updatedCar, CarDto.class);
	}

	@Override
	public List<CarDto> getByRandom() {
		List<Car> cars = carRepo.findByRandom();
		List<CarDto> carDtos = cars.stream().map(car -> modelMapper.map(car, CarDto.class)).collect(Collectors.toList());
		return carDtos;
	}

	@Override
	public CarResponse getAllCars(Integer pageNumber, Integer pageSize, String sortBy) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
		Page<Car> pages = carRepo.findAll(pageable);
		
		List<Car> cars = pages.getContent();
		List<CarDto> carDtos = cars.stream().map(car -> modelMapper.map(car, CarDto.class)).collect(Collectors.toList());
		
		CarResponse carResponse = new CarResponse();
		carResponse.setPageContent(carDtos);
		carResponse.setPageNumber(pages.getNumber());
		carResponse.setPageSize(pages.getSize());
		carResponse.setTotalPages(pages.getTotalPages());
		carResponse.setIsFirstPage(pages.isFirst());
		carResponse.setIsLastPage(pages.isLast());
		
		return carResponse;
	}

	@Override
	public CarResponse getBySearch(String keyword, Integer pageNumber, Integer pageSize, String sortBy) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
		Page<Car> pages = carRepo.findByModelNameContaining(keyword, pageable);
		
		List<Car> cars = pages.getContent();
		List<CarDto> carDtos = cars.stream().map(car -> modelMapper.map(car, CarDto.class)).collect(Collectors.toList());
		
		CarResponse carResponse = new CarResponse();
		carResponse.setPageContent(carDtos);
		carResponse.setPageNumber(pages.getNumber());
		carResponse.setPageSize(pages.getSize());
		carResponse.setTotalPages(pages.getTotalPages());
		carResponse.setIsFirstPage(pages.isFirst());
		carResponse.setIsLastPage(pages.isLast());
		
		return carResponse;
	}

	@Override
	public CarResponse getByFilter(String mileage, String seatCapacity, String bootSpace, String rentPrice, Integer pageNumber, Integer pageSize, String sortBy) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
		Page<Car> pages = carRepo.findByFilter(mileage, seatCapacity, bootSpace, rentPrice, pageable);
		
		List<Car> cars = pages.getContent();
		List<CarDto> carDtos = cars.stream().map(car -> modelMapper.map(car, CarDto.class)).collect(Collectors.toList());
		
		CarResponse carResponse = new CarResponse();
		carResponse.setPageContent(carDtos);
		carResponse.setPageNumber(pages.getNumber());
		carResponse.setPageSize(pages.getSize());
		carResponse.setTotalPages(pages.getTotalPages());
		carResponse.setIsFirstPage(pages.isFirst());
		carResponse.setIsLastPage(pages.isLast());
		
		return carResponse;
	}

	@Override
	public CarFilterRequest getFilterValues() {
		CarFilterRequest carFilterRequest = new CarFilterRequest();
		carFilterRequest.setMaxMileage(carRepo.findMaxMileage());
		carFilterRequest.setMinMileage(carRepo.findMinMileage());
		carFilterRequest.setMaxSeatCapacity(carRepo.findMaxSeatCapacity());
		carFilterRequest.setMinSeatCapacity(carRepo.findMinSeatCapacity());
		carFilterRequest.setMaxBootSpace(carRepo.findMaxBootSpace());
		carFilterRequest.setMinBootSpace(carRepo.findMinBootSpace());
		carFilterRequest.setMaxRentPrice(carRepo.findMaxRentPrice());
		carFilterRequest.setMinRentPrice(carRepo.findMinRentPrice());
		return carFilterRequest;
	}

	@Override
	public void removeCarModel(Integer modelId) {
		Car car = carRepo.findById(modelId).orElseThrow(() -> new ResourceNotFoundException("Car", "Model ID", modelId));
		carRepo.delete(car);
	}

}
