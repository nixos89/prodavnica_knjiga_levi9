package com.levi9.prodavnica.dto;

import java.util.Set;

import com.levi9.prodavnica.model.Author;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TopSellingBookDTO {
	
	private String bookName;
	private Set<Author> authors;
	private int amount;
	
}
