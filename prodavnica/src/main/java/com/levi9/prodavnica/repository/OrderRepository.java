package com.levi9.prodavnica.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.levi9.prodavnica.dto.OrderDTO;
import com.levi9.prodavnica.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
		
	public List<Order> findAll();
	
	
	
}
