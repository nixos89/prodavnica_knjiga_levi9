package com.levi9.prodavnica.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.levi9.prodavnica.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query(value = "SELECT * FROM book as b left join book_author as ba on b.book_id=ba.book_id left join author a" +
			" on  ba.author_id=a.author_id where b.name like %?1% or a.first_name like %?1% or a.last_name like %?1% " ,nativeQuery = true)
    List<Book> getBookSearch(String search);


    @Transactional
	@Modifying
	@Query(value = "DELETE FROM book_author b WHERE b.book_id =:idBook", nativeQuery = true)
	public void deleteAuthorsFromBook(@Param("idBook") Long idBook);
    
    @Transactional
	@Modifying
	@Query(value = "DELETE FROM book_category b WHERE b.book_id =:idBook", nativeQuery = true)
	public void deleteCategoriesFromBook(@Param("idBook") Long idBook);

}
