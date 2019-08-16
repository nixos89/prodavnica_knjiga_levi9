package com.levi9.prodavnica.dto;

import java.util.Set;

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
	private Set<AuthorDTO> authors;
	private int amount;
	
}
