package com.levi9.prodavnica.config;

import java.util.HashSet;
import java.util.Set;

import com.levi9.prodavnica.dto.AddUpdateBookDTO;

public class BookConstants {

	public static final Long book0id = 1L;
	public static final String book0name = "test";
	public static final double book0price = 10;
	public static final int book0amount = 10;
	public static final boolean book0deleted = false;
	public static final Set<Long> book0authors = new HashSet<>();
	public static final Set<Long> book0categories = new HashSet<>();
	static {
		book0authors.add(1L);
		book0authors.add(2L);
		book0categories.add(1L);
		book0categories.add(2L);
	}

	public static final Long book1id = 2L;
	public static final String book1name = "test1";
	public static final double book1price = 100;
	public static final int book1amount = 100;
	public static final boolean book1deleted = false;

	public static final AddUpdateBookDTO addUpdateDTO = new AddUpdateBookDTO(BookConstants.book0name,
			BookConstants.book0price, BookConstants.book0amount, BookConstants.book0deleted, BookConstants.book0authors,
			BookConstants.book0categories);



}
