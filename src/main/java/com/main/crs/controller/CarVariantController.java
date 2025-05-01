package com.main.crs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.crs.dto.CarVariantDto;
import com.main.crs.payload.ApiResponse;
import com.main.crs.service.CarVariantService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/crs/variants")
public class CarVariantController {
	
	@Autowired
	CarVariantService carVariantService;
	
	@PostMapping("/{modelId}")
	public ResponseEntity<CarVariantDto> addCarVariant(@Valid @RequestBody CarVariantDto carVariantDto, @PathVariable Integer modelId) {
		CarVariantDto createdVariant = carVariantService.addCarVariant(carVariantDto, modelId);
		return new ResponseEntity<>(createdVariant, HttpStatus.CREATED);
	}
	
	@GetMapping("/{registration}")
	public ResponseEntity<CarVariantDto> getVariantById(@PathVariable String registration) {
		CarVariantDto carVariantDto = carVariantService.getVariantById(registration);
		return new ResponseEntity<>(carVariantDto, HttpStatus.OK);
	}
	
	@PutMapping("/{registration}")
	public ResponseEntity<CarVariantDto> updateVariant(@Valid @RequestBody CarVariantDto carVariantDto, @PathVariable String registration) {
		CarVariantDto updatedVariant = carVariantService.updateVariant(carVariantDto, registration);
		return new ResponseEntity<>(updatedVariant, HttpStatus.OK);
	}
	
	@GetMapping("/car/{modelId}")
	public ResponseEntity<List<CarVariantDto>> getVariantsByModel(@PathVariable Integer modelId) {
		List<CarVariantDto> carVariantDtos = carVariantService.getVariantsByCar(modelId);
		return new ResponseEntity<>(carVariantDtos, HttpStatus.OK);
	}
	
	@DeleteMapping("/{registration}")
	public ResponseEntity<ApiResponse> removeCarVariant(@PathVariable String registration) {
		carVariantService.removeCarVariant(registration);
		return new ResponseEntity<>(new ApiResponse("Variant Removed", true), HttpStatus.OK);
	}
	
	@GetMapping("/clear")
	public ResponseEntity<ApiResponse> clearCarVariants() {
		carVariantService.clearCarVariants();
		return new ResponseEntity<>(new ApiResponse("Variants Cleared", true), HttpStatus.OK);
	}

}
