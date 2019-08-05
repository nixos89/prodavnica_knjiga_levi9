package com.levi9.prodavnica.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private double price;

	private int amount;

	private boolean isDeleted;

	@OneToMany(mappedBy = "book", cascade = CascadeType.REFRESH)
	private Set<BookAuthor> bookAuthor = new HashSet<BookAuthor>();

	@OneToMany(mappedBy = "book", cascade = CascadeType.REFRESH)
	private Set<BookCategory> bookCategory = new HashSet<BookCategory>();

	public Book(String name, double price, int amount, boolean isDeleted) {
		this.name = name;
		this.price = price;
		this.amount = amount;
		this.isDeleted = isDeleted;
	}

}
