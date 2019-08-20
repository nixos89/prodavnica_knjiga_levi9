package com.levi9.prodavnica.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderReportDTO {
	
	private List<OrderDTO> orderDTOList;

	public OrderReportDTO() {
		this.orderDTOList = new ArrayList<OrderDTO>();
	}	
	
}
