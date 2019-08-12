package com.levi9.prodavnica.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "book")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bookId;

	private String name;

	private double price;

	private int amount;

	private boolean isDeleted;

	@ManyToMany(mappedBy = "books", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Set<Author> authors;

	@ManyToMany(mappedBy = "books", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Set<Category> categories;

	@OneToMany(mappedBy = "book", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Set<OrderItem> orderItems;

	public Book(String name, double price, int amount, boolean isDeleted) {
		this.name = name;
		this.price = price;
		this.amount = amount;
		this.isDeleted = isDeleted;
	}

}
