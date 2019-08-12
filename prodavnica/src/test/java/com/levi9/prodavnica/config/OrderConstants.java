package com.levi9.prodavnica.config;

import java.util.HashSet;
import java.util.Set;

import com.levi9.prodavnica.dto.AddOrderDTO;
import com.levi9.prodavnica.dto.OrderListDTO;
import com.levi9.prodavnica.dto.OrderResponseDTO;

public class OrderConstants {

	public static final double order0total = 100;
	public static final int order0amount = 10;
	public static final Long order0bookId = 1L;
	public static final Long order0orderId = 1L;

	public static final Set<AddOrderDTO> addOrder = new HashSet<>();
	static {
		addOrder.add(new AddOrderDTO(order0amount, order0bookId));
	}
	public static final OrderListDTO orderListDTO = new OrderListDTO(addOrder, order0total);
	public static final OrderResponseDTO orderResponse = new OrderResponseDTO(order0orderId);
}
