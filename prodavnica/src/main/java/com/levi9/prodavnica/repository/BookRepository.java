package com.levi9.prodavnica.repository;

import com.levi9.prodavnica.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query(value = "select b.book_id from book as b right join book_category  as bc on b.book_id = bc.book_id where bc.category_id=?1",nativeQuery = true)
    List<Long> getBooksFromCategory(Long id);
}
