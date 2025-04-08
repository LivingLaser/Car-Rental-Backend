package com.main.crs.payload;

import java.util.List;

import com.main.crs.dto.UserDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
	
	private List<UserDto> pageContent;
	private Integer pageNumber;
	private Integer pageSize;
	private Long totalElements;
	private Integer totalPages;
	private Boolean isFirstPage;
	private Boolean isLastPage;

}
