package com.levi9.prodavnica.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderListDTO {

	private Set<AddOrderDTO> orders;
	private double total;

}
