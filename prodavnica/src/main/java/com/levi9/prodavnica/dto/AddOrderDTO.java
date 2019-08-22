package com.levi9.prodavnica.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddOrderDTO {

	@NotNull(message = "Amount is required")
	private int amount;
	@NotNull(message = "Book is required")
	private Long bookId;

}
