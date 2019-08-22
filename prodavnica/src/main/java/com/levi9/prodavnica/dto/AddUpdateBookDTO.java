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
public class AddUpdateBookDTO {

	@NotEmpty(message = "Title of book is required")
	private String name;
	@NotNull(message = "Price is required")
	private double price;
	@NotNull(message = "Amount is required")
	private int amount;
	private boolean deleted;
	@NotNull(message = "Categories are required")
	private Set<Long> categoryIds;
	@NotNull(message = "Authors are required")
	private Set<Long> authorIds;

}