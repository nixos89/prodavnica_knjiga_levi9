package com.levi9.prodavnica.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderListDTO {

	@NotNull(message = "Orders are required")
	private Set<AddOrderDTO> orders;
	@NotNull(message = "Total price is required")
	private double total;

}
