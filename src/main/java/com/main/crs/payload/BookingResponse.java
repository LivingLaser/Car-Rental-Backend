package com.main.crs.payload;

import java.util.List;

import com.main.crs.dto.BookingDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingResponse {
	
	private List<BookingDto> pageContent;
	private Integer pageNumber;
	private Integer pageSize;
	private Integer totalPages;
	private Boolean isFirstPage;
	private Boolean isLastPage;

}
