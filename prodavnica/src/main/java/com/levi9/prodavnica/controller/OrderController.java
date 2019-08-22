package com.levi9.prodavnica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.levi9.prodavnica.dto.OrderListDTO;
import com.levi9.prodavnica.service.OrderService;

import java.security.Principal;

@RestController
@RequestMapping("api/orders")
@CrossOrigin(origins = "*")
public class OrderController {

	@Autowired
	OrderService orderService;

	@PreAuthorize(value = "hasAuthority('USER') or hasRole('USER')")
	@PostMapping
	public ResponseEntity<?> addOrder(@RequestBody @Validated OrderListDTO orderRequest, Principal principal) {
		return ResponseEntity.ok(orderService.addOrder(orderRequest, principal.getName()));
	}

}
