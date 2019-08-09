package com.levi9.prodavnica.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.levi9.prodavnica.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

	@Query(value = "select b.book_id from book as b right join book_category  as bc on b.book_id = bc.book_id where bc.category_id =:id", nativeQuery = true)
	public List<Long> getBooksFromCategories(@Param("id") Long id);

}
