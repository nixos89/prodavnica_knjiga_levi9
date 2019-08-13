package com.levi9.prodavnica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.levi9.prodavnica.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
