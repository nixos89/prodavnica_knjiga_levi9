package com.levi9.prodavnica.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO {
	
	private Long orderItemId;
	private Long orderId;
	private BookDTO bookDTO;
	private int orderedAmount;	
	private double totalOrderedItemPrice;
	
}
