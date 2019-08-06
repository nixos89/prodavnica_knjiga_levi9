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
@Table(name="book_author")
public class BookAuthor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bookAuthorId;

	@ManyToOne
	private Book book;

	@ManyToOne
	private Author author;

	public BookAuthor(Book book, Author author) {
		this.book = book;
		this.author = author;
	}

}
