package com.main.crs.payload;

import java.util.List;

import com.main.crs.dto.CarDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarResponse {
	
	private List<CarDto> pageContent;
	private Integer pageNumber;
	private Integer totalPages;

}
