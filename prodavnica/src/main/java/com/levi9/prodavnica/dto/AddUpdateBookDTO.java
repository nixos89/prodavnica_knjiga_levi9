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
public class AddUpdateBookDTO {

	private String name;
	private double price;
	private int amount;
	private boolean deleted;
	private Set<Long> categoryIds;
	private Set<Long> authorIds;

}