package com.levi9.prodavnica.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="category")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long categoryId;

	private String name;

	private boolean isDeleted;

	@ManyToMany(fetch =FetchType.EAGER ,cascade =  {CascadeType.PERSIST ,CascadeType.MERGE})
	@JoinTable(name = "book_category", joinColumns = { @JoinColumn(name = "category_id") }, inverseJoinColumns = {
			@JoinColumn(name = "book_id") })
	private Set<Book> books = new HashSet<>();

	public Category(String name) {
		this.name = name;
	}

}
