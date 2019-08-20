package com.levi9.prodavnica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.levi9.prodavnica.dto.OrderListDTO;
import com.levi9.prodavnica.service.OrderService;

@RestController
@RequestMapping("api/orders")
@CrossOrigin(origins = "*")
public class OrderController {

	@Autowired
	OrderService orderService;

	@PostMapping
	public ResponseEntity<?> addOrder(@RequestBody OrderListDTO orderRequest) {
		return ResponseEntity.ok(orderService.addOrder(orderRequest));
	}

	@GetMapping
	public ResponseEntity<?> getOrderReport(){
		return ResponseEntity.ok(orderService.getOrderReport());
	}
}
