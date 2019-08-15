package com.levi9.prodavnica.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.levi9.prodavnica.dto.TopSellingBookDTO;
import com.levi9.prodavnica.dto.TopSellingBookListDTO;
import com.levi9.prodavnica.model.OrderItem;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
	
	// returns list of most sold bookIds
	@Query("SELECT oi.book.bookId, oi.amount FROM OrderItem oi JOIN oi.book b WHERE b.amount > 0 AND b.isDeleted = false GROUP BY oi.book.bookId ORDER BY SUM(oi.amount)")
    public Map<Long, Integer> getTop10SellingBooks();
	
//	@Query("SELECT NEW com.levi9.prodavnica.dto.TopSellingBookDTO(oi.book.name, oi.book.authors, oi.amount) FROM OrderItem oi JOIN oi.book b WHERE b.amount > 0 AND b.isDeleted = false GROUP BY oi.book.bookId")
//    public List<TopSellingBookDTO> getTopSellingBooks();
	
}
