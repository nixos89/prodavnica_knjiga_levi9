package com.levi9.prodavnica.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TopSellingBookListDTO {
	
	List<TopSellingBookDTO> topSellingBookList;
	
	public TopSellingBookListDTO() {
		this.topSellingBookList = new ArrayList<>();
	}
	
}
