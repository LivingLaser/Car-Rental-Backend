package com.main.crs.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.main.crs.config.AppConstants;
import com.main.crs.dto.CarDto;
import com.main.crs.payload.ApiResponse;
import com.main.crs.payload.CarFilterRequest;
import com.main.crs.payload.CarResponse;
import com.main.crs.service.CarService;
import com.main.crs.service.FileService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/crs/cars")
public class CarController {
	
	@Autowired
	private CarService carService;
	
	@Autowired
	private FileService fileService;
	
	@PostMapping("/")
	public ResponseEntity<CarDto> addCarModel(@Valid @RequestBody CarDto carDto) {
		CarDto createdModel = carService.addCarModel(carDto);
		return new ResponseEntity<>(createdModel, HttpStatus.CREATED);
	}
	
	@GetMapping("/{modelId}")
	public ResponseEntity<CarDto> getModelById(@PathVariable Integer modelId) {
		CarDto carDto = carService.getModelById(modelId);
		return new ResponseEntity<>(carDto, HttpStatus.OK);
	}
	
	@PutMapping("/{modelId}")
	public ResponseEntity<CarDto> updateModel(@Valid @RequestBody CarDto carDto, @PathVariable Integer modelId) {
		CarDto updatedModel = carService.updateModel(carDto, modelId);
		return new ResponseEntity<>(updatedModel, HttpStatus.OK);
	}
	
	@GetMapping("/random")
	public ResponseEntity<List<CarDto>> getByRandom() {
		List<CarDto> carDtos = carService.getByRandom();
		return new ResponseEntity<>(carDtos, HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<CarResponse> getAllCars(
			@RequestParam(defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(defaultValue = AppConstants.CAR_SORT_BY, required = false) String sortBy) {
		CarResponse carResponse = carService.getAllCars(pageNumber, pageSize, sortBy);
		return new ResponseEntity<>(carResponse, HttpStatus.OK);
	}
	
	@GetMapping("/search/{keyword}")
	public ResponseEntity<CarResponse> getBySearch(@PathVariable String keyword,
			@RequestParam(defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(defaultValue = AppConstants.CAR_SORT_BY, required = false) String sortBy) {
		CarResponse carResponse = carService.getBySearch(keyword, pageNumber, pageSize, sortBy);
		return new ResponseEntity<>(carResponse, HttpStatus.OK);
	}
	
	@GetMapping("/filter")
	public ResponseEntity<CarResponse> getByFilter(@RequestParam String mileage,
			@RequestParam String seatCapacity, @RequestParam String bootSpace, @RequestParam String rentPrice,
			@RequestParam(defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(defaultValue = AppConstants.CAR_SORT_BY_NATIVE, required = false) String sortBy) {
		CarResponse carResponse = carService.getByFilter(mileage, seatCapacity, bootSpace, rentPrice, pageNumber, pageSize, sortBy);
		return new ResponseEntity<>(carResponse, HttpStatus.OK);
	}
	
	@GetMapping("/filter_values")
	public ResponseEntity<CarFilterRequest> getFilterValues() {
		CarFilterRequest carFilterRequest = carService.getFilterValues();
		return new ResponseEntity<>(carFilterRequest, HttpStatus.OK);
	}
	
	@DeleteMapping("/{modelId}")
	public ResponseEntity<ApiResponse> removeCarModel(@PathVariable Integer modelId) {
		carService.removeCarModel(modelId);
		return new ResponseEntity<>(new ApiResponse("Model Removed", true), HttpStatus.OK);
	}
	
	@PostMapping("/upload/image/{modelId}")
	public ResponseEntity<CarDto> uploadImage(@PathVariable Integer modelId, @RequestParam MultipartFile image) throws IOException {
		CarDto carDto = carService.getModelById(modelId);
		String fileName = fileService.uploadImage(AppConstants.CAR_IMAGE_PATH, image);
		carDto.setModelImage(fileName);
		CarDto updatedModel = carService.updateModel(carDto, modelId);
		return new ResponseEntity<>(updatedModel, HttpStatus.OK);
	}
	
	@GetMapping(value = "/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void getImageResource(@PathVariable String imageName, HttpServletResponse response) throws IOException {
		InputStream imageResource = fileService.getImageResource(AppConstants.CAR_IMAGE_PATH, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(imageResource, response.getOutputStream());
	}

}
