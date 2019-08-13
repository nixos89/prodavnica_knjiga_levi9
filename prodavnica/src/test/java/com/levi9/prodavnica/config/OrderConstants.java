package com.levi9.prodavnica.config;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.levi9.prodavnica.dto.AddOrderDTO;
import com.levi9.prodavnica.dto.OrderListDTO;
import com.levi9.prodavnica.dto.OrderResponseDTO;
import com.levi9.prodavnica.model.Book;
import com.levi9.prodavnica.model.Order;

public class OrderConstants {

	public static final double order0total = 100;
	public static final int order0amount = 5;
	public static final Long order0bookId = 1L;
	public static final Long order0orderId = 1L; 

	public static final int order1amount = 20;

	public static final Set<AddOrderDTO> addOrders = new HashSet<>();
	public static final AddOrderDTO addOrderRequestExpected = new AddOrderDTO(OrderConstants.order0amount,
			OrderConstants.order0bookId);
	static {
		addOrders.add(addOrderRequestExpected);
	}

	public static final OrderListDTO orderRequest = new OrderListDTO(addOrders, OrderConstants.order0total);
	public static final OrderResponseDTO createResponseExpected = new OrderResponseDTO(order0orderId);

	public static final Book bookResponseExpected = new Book(BookConstants.book0id, BookConstants.book0name,
			BookConstants.book0price, BookConstants.book0amount, BookConstants.book0deleted);
	public static final Order orderResponseExpected = new Order(1L, 10.5, new Date());
	public static final OrderListDTO orderListDTO = new OrderListDTO(addOrders, order0total);

	public static final OrderListDTO orderListNull = null;

}
