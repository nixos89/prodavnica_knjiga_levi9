package com.levi9.prodavnica.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="author")
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long authorId;

	private String firstName;

	private String lastName;

	@ManyToMany(fetch = FetchType.EAGER, cascade =  {CascadeType.PERSIST ,CascadeType.MERGE})
	@JoinTable(name = "book_author", joinColumns = { @JoinColumn(name = "author_id") }, inverseJoinColumns = {@JoinColumn(name = "book_id") })
	private Set<Book> books;

	public Author(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

}
