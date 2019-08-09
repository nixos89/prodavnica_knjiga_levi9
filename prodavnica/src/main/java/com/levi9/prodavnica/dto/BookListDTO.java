package com.levi9.prodavnica.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BookListDTO {

	List<BookDTO> books;

	public BookListDTO() {
		this.books = new ArrayList<>();
	}

}
