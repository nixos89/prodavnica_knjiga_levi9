package com.levi9.prodavnica.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.levi9.prodavnica.model.OrderItem;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
	
	@Query("SELECT oi.book.bookId, SUM(oi.amount) FROM OrderItem oi JOIN oi.book b WHERE b.amount > 0 AND b.isDeleted = false GROUP BY oi.book.bookId ORDER BY SUM(oi.amount) DESC")
    public List<Object[]> getTopSellingBooks();
	
}
