package com.levi9.prodavnica.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="book_category")
public class BookCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bookCategoryId;

	@ManyToOne
	private Book book;

	@ManyToOne
	private Category category;

	public BookCategory(Book book, Category category) {
		this.book = book;
		this.category = category;
	}

}
