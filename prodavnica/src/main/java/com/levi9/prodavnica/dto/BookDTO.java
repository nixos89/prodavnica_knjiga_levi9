package com.levi9.prodavnica.dto;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class BookDTO {

	private Long bookId;
	private String name;
	private double price;
	private int amount;
	private boolean deleted;
	private Set<AuthorDTO> authors = new HashSet<>();
	private Set<CategoryDTO> categories = new HashSet<>();

	public BookDTO(Long bookId, String name, double price, int amount, boolean deleted) {
		this.bookId = bookId;
		this.name = name;
		this.price = price;
		this.amount = amount;
		this.deleted = deleted;
	}

}
