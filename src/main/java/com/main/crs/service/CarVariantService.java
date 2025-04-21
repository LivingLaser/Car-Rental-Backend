package com.main.crs.service;

import java.util.List;

import com.main.crs.dto.CarVariantDto;

public interface CarVariantService {
	
	CarVariantDto addCarVariant(CarVariantDto carVariantDto, Integer modelId);
	CarVariantDto getVariantById(String registration);
	CarVariantDto updateVariant(CarVariantDto carVariantDto, String registration);
	List<CarVariantDto> getVariantsByCar(Integer modelId);
	void removeCarVariant(String registration);

}
