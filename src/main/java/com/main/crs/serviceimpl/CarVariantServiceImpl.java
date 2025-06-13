package com.main.crs.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.crs.dto.CarVariantDto;
import com.main.crs.entity.Car;
import com.main.crs.entity.CarVariant;
import com.main.crs.entity.User;
import com.main.crs.exception.ResourceNotFoundException;
import com.main.crs.repository.CarRepo;
import com.main.crs.repository.CarVariantRepo;
import com.main.crs.repository.UserRepo;
import com.main.crs.service.CarVariantService;

@Service
public class CarVariantServiceImpl implements CarVariantService {
	
	@Autowired
	private CarVariantRepo carVariantRepo;
	
	@Autowired
	private CarRepo carRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CarVariantDto addCarVariant(CarVariantDto carVariantDto, Integer modelId, Integer userId) {
		Car car = carRepo.findById(modelId).orElseThrow(() -> new ResourceNotFoundException("Car", "Model ID", modelId));
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "ID", userId));
		CarVariant carVariant = modelMapper.map(carVariantDto, CarVariant.class);
		carVariant.setCar(car);
		carVariant.setUser(user);
		CarVariant savedVariant = carVariantRepo.save(carVariant);
		return modelMapper.map(savedVariant, CarVariantDto.class);
	}

	@Override
	public CarVariantDto getVariantById(String registration) {
		CarVariant carVariant = carVariantRepo.findById(registration).orElseThrow(() -> new ResourceNotFoundException("Vehicle", registration + " Registration", 404));
		return modelMapper.map(carVariant, CarVariantDto.class);
	}

	@Override
	public CarVariantDto updateVariant(CarVariantDto carVariantDto, String registration) {
		CarVariant carVariant = carVariantRepo.findById(registration).orElseThrow(() -> new ResourceNotFoundException("Vehicle", registration + " Registration", 404));
		carVariant.setInsuranceValidity(carVariantDto.getInsuranceValidity());
		carVariant.setPucValidity(carVariantDto.getPucValidity());
		carVariant.setModelColor(carVariantDto.getModelColor());
		CarVariant updatedVariant = carVariantRepo.save(carVariant);
		return modelMapper.map(updatedVariant, CarVariantDto.class);
	}

	@Override
	public List<CarVariantDto> getVariantsByCar(Integer modelId) {
		Car car = carRepo.findById(modelId).orElseThrow(() -> new ResourceNotFoundException("Car", "Model ID", modelId));
		List<CarVariant> carVariants = carVariantRepo.findByCar(car);
		List<CarVariantDto> carVariantDtos = carVariants.stream().map(carVariant -> modelMapper.map(carVariant, CarVariantDto.class)).collect(Collectors.toList());
		return carVariantDtos;
	}

	@Override
	public List<CarVariantDto> getVariantsByOwner(Integer userId) {
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "ID", userId));
		List<CarVariant> carVariants = carVariantRepo.findByUser(user);
		List<CarVariantDto> carVariantDtos = carVariants.stream().map(carVariant -> modelMapper.map(carVariant, CarVariantDto.class)).collect(Collectors.toList());
		return carVariantDtos;
	}

	@Override
	public void removeCarVariant(String registration) {
		CarVariant carVariant = carVariantRepo.findById(registration).orElseThrow(() -> new ResourceNotFoundException("Vehicle", registration + " Registration", 404));
		carVariantRepo.delete(carVariant);
	}

	@Override
	public List<CarVariantDto> getActiveVariants(Integer modelId) {
		Car car = carRepo.findById(modelId).orElseThrow(() -> new ResourceNotFoundException("Car", "Model ID", modelId));
		List<CarVariant> carVariants = carVariantRepo.findByCarAndStatus(car, "Active");
		List<CarVariantDto> carVariantDtos = carVariants.stream().map(carVariant -> modelMapper.map(carVariant, CarVariantDto.class)).collect(Collectors.toList());
		return carVariantDtos;
	}

	@Override
	public void clearCarVariants() {
		List<CarVariant> carVariants = carVariantRepo.clearVariants();
		carVariants.forEach(carVariant -> carVariant.setStatus("Active"));
		carVariantRepo.saveAll(carVariants);
	}

}
