package com.levi9.prodavnica.config;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.levi9.prodavnica.dto.AddOrderDTO;
import com.levi9.prodavnica.dto.BookDTO;
import com.levi9.prodavnica.dto.OrderDTO;
import com.levi9.prodavnica.dto.OrderItemDTO;
import com.levi9.prodavnica.dto.OrderListDTO;
import com.levi9.prodavnica.dto.OrderReportDTO;
import com.levi9.prodavnica.dto.OrderResponseDTO;
import com.levi9.prodavnica.model.Book;
import com.levi9.prodavnica.model.Order;

public class OrderConstants {

	public static final double order0total = 100; // order total price
	public static final int order0amount = 5;
	public static final Long order0bookId = 1L;
	public static final Long order0orderId = 1L;
	public static final Long order0orderItem0Id = 1L;
	public static final Long order0orderItem1Id = 2L;
	public static final double order0orderPrice = 50;
	public static final String order0Date = "21/08/2019 10:15:16";
	
	public static final int order0orderItem0amount = 4;
	public static final int order0orderItem1amount = 3;
	
	public static final double order0Price = BookConstants.book0price * order0orderItem0amount;

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
	
	public static final OrderReportDTO orderReportDTO = new OrderReportDTO();
	public static final List<OrderDTO> orderDTOList = orderItemDTOs();
	
	
	
	public static List<OrderDTO> orderItemDTOs(){
		LinkedList<OrderItemDTO> orderItemDTOList = new LinkedList<OrderItemDTO>();
		
		OrderItemDTO oi0DTO1 = new OrderItemDTO();
		oi0DTO1.setOrderId(order0orderId);
		oi0DTO1.setOrderedAmount(order0orderItem0amount);
		oi0DTO1.setOrderItemId(order0orderItem0Id);
		oi0DTO1.setTotalOrderedItemPrice(order0orderItem0amount * BookConstants.book0price);
		oi0DTO1.setBookDTO(new BookDTO(BookConstants.book0id, BookConstants.book0name, BookConstants.book0price, BookConstants.book0amount, BookConstants.book0deleted));
		
		OrderItemDTO oi0DTO2 = new OrderItemDTO();
		oi0DTO2.setOrderId(order0orderId);
		oi0DTO2.setOrderedAmount(order0orderItem1amount);
		oi0DTO2.setOrderItemId(order0orderItem1Id);
		oi0DTO2.setTotalOrderedItemPrice(order0orderItem1amount * BookConstants.book1price);
		oi0DTO2.setBookDTO(new BookDTO(BookConstants.book0id, BookConstants.book0name, BookConstants.book0price, BookConstants.book0amount, BookConstants.book0deleted));
		
		orderItemDTOList.add(oi0DTO1);
		orderItemDTOList.add(oi0DTO2);
		
		List<OrderDTO> orderDTOListNew = new ArrayList<>();
		OrderDTO oDTO0 = new OrderDTO(order0orderId, orderItemDTOList, order0Date, order0Price);
		orderDTOListNew.add(oDTO0);
		
		return orderDTOListNew;
	}
	
	

}
