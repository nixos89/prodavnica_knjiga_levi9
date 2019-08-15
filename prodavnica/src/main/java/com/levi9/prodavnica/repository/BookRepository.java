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

    @Query(value = "select b.book_id from book as b right join book_category  as bc on b.book_id = bc.book_id where bc.category_id=?1",nativeQuery = true)
    List<Long> getBooksFromCategory(Long id);
    
    @Transactional
	@Modifying
	@Query(value = "DELETE FROM book_author b WHERE b.book_id =:idBook", nativeQuery = true)
	public void deleteAuthorsFromBook(@Param("idBook") Long idBook);
    
    @Transactional
	@Modifying
	@Query(value = "DELETE FROM book_category b WHERE b.book_id =:idBook", nativeQuery = true)
	public void deleteCategoriesFromBook(@Param("idBook") Long idBook);

}
