package com.main.crs.service;

import java.util.List;

import com.main.crs.dto.CarDto;
import com.main.crs.payload.CarFilterRequest;
import com.main.crs.payload.CarResponse;

public interface CarService {
	
	CarDto addCarModel(CarDto carDto);
	CarDto getModelById(Integer modelId);
	CarDto updateModel(CarDto carDto, Integer modelId);
	List<CarDto> getByRandom();
	CarResponse getAllCars(Integer pageNumber, Integer pageSize, String sortBy);
	CarResponse getBySearch(String keyword, Integer pageNumber, Integer pageSize, String sortBy);
	CarResponse getByFilter(String mileage, String seatCapacity, String bootSpace, String rentPrice, Integer pageNumber, Integer pageSize, String sortBy);
	CarFilterRequest getFilterValues();
	void removeCarModel(Integer modelId);

}
