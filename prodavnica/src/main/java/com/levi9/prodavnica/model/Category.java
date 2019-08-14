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

	@ManyToMany(fetch =FetchType.EAGER ,mappedBy = "categories")
	private Set<Book> books = new HashSet<>();

	public Category(String name) {
		this.name = name;
	}

	public Category(Long categoryId, String name , boolean isDeleted){
		this.categoryId = categoryId;
		this.name = name;
		this.isDeleted = isDeleted;
	}
}
