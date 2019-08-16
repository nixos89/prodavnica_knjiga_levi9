package com.levi9.prodavnica.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.levi9.prodavnica.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

	@Query("SELECT b.bookId FROM Book b RIGHT JOIN b.categories c WHERE b.isDeleted = 0 AND b.amount > 0 AND c.categoryId IN :ids")
	public List<Long> getBooksFromCategories(@Param("ids") Set<Long> ids);

	@Query("SELECT b FROM Book b WHERE b.isDeleted = 0 AND b.amount > 0")
	public List<Book> getAllBooks();

}
