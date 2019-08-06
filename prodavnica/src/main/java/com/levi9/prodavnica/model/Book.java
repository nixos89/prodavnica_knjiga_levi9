package com.levi9.prodavnica.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ManyToAny;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="book")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bookId;

	private String name;

	private double price;

	private int amount;

	private boolean isDeleted;

	@ManyToMany(mappedBy = "books", cascade = CascadeType.ALL)
	private Set<Author> authors;

	@ManyToMany(mappedBy = "books", cascade = CascadeType.ALL)
	private Set<Category> categories;

	public Book(String name, double price, int amount, boolean isDeleted) {
		this.name = name;
		this.price = price;
		this.amount = amount;
		this.isDeleted = isDeleted;
	}

}
