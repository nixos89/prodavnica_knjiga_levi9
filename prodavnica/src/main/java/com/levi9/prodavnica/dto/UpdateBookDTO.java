package com.levi9.prodavnica.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UpdateBookDTO {

	private String name;
	private double price;
	private int amount;
	private boolean isDeleted;

}
