package com.levi9.prodavnica.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AddUpdateBookDTO {

	private String name;
	private double price;
	private int amount;
	private boolean isDeleted;
	private Set<Long> categoryIds;
	private Set<Long> authorIds;

}