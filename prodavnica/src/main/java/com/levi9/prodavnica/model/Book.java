package com.levi9.prodavnica.model;

import java.util.Set;

import javax.persistence.*;

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

	@ManyToMany(fetch = FetchType.EAGER,cascade = { CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "book_author", joinColumns = { @JoinColumn(name = "book_id") }, inverseJoinColumns = {@JoinColumn(name = "author_id") })
	private Set<Author> authors;

	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "book_category", joinColumns = { @JoinColumn(name = "book_id") }, inverseJoinColumns = {@JoinColumn(name = "category_id") })
	private Set<Category> categories;

	@OneToMany(mappedBy = "book", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Set<OrderItem> orderItems;

	public Book(Long bookId, String name, double price, int amount, boolean isDeleted) {
		this.bookId = bookId;
		this.name = name;
		this.price = price;
		this.amount = amount;
		this.isDeleted = isDeleted;
	}

}
