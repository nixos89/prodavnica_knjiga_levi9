package com.levi9.prodavnica.config;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.levi9.prodavnica.dto.AddOrderDTO;
import com.levi9.prodavnica.dto.AuthorDTO;
import com.levi9.prodavnica.dto.BookDTO;
import com.levi9.prodavnica.dto.CategoryDTO;
import com.levi9.prodavnica.dto.OrderDTO;
import com.levi9.prodavnica.dto.OrderItemDTO;
import com.levi9.prodavnica.dto.OrderListDTO;
import com.levi9.prodavnica.dto.OrderReportDTO;
import com.levi9.prodavnica.dto.OrderResponseDTO;
import com.levi9.prodavnica.dto.UserDTO;
import com.levi9.prodavnica.model.Book;
import com.levi9.prodavnica.model.Order;
import com.levi9.prodavnica.model.OrderItem;
import com.levi9.prodavnica.model.User;

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
	public static final List<OrderDTO> orderDTOList = orderDTOList();

	public static List<Order> getAllOrders() {
		Order order1 = new Order();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date dateOrder1 = null;
		try {
			dateOrder1 = sdf.parse(order0Date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		order1.setOrderDate(dateOrder1);
		order1.setOrderId(order0orderId);
		order1.setUser(new User(1L, "test"));
		Set<OrderItem> order1Items = orderItems();

		order1Items.addAll(order1Items);
		order1.setOrderItems(order1Items);

		LinkedList<Order> orders = new LinkedList<Order>();
		orders.add(order1);

		return orders;
	}
	
	public static List<Order> getAllOrders_withNullDate() {
		Order order1 = new Order();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date dateOrder1 = null;
		try {
			dateOrder1 = sdf.parse(order0Date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		order1.setOrderDate(null);
		order1.setOrderId(order0orderId);

		Set<OrderItem> order1Items = orderItems();

		order1Items.addAll(order1Items);
		order1.setOrderItems(order1Items);
		order1.setUser(new User(1L, "test"));
		
		LinkedList<Order> orders = new LinkedList<Order>();
		orders.add(order1);
		
		return orders;
	}

	public static Set<OrderItem> orderItems() {
		Set<OrderItem> oItems = new HashSet<OrderItem>();
		OrderItem oi1 = new OrderItem();
		oi1.setAmount(order0orderItem0amount);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date dateOrder1 = null;
		try {
			dateOrder1 = sdf.parse(order0Date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		oi1.setOrder(new Order(order0orderId, order0Price, dateOrder1));
		oi1.setBook(new Book(order0bookId, BookConstants.book0name, BookConstants.book0price, BookConstants.book0amount,
				BookConstants.book0deleted));
		oi1.setOrderItemId(order0orderItem0Id);
		oItems.add(oi1);

		return oItems;
	}

	public static List<OrderDTO> orderDTOList() {
		LinkedList<OrderItemDTO> orderItemDTOList = new LinkedList<OrderItemDTO>();
		UserDTO userDTO = new UserDTO(1L, "test");
		
		OrderItemDTO oi0DTO1 = new OrderItemDTO();
		oi0DTO1.setOrderId(order0orderId);
		oi0DTO1.setOrderedAmount(order0orderItem0amount);
		oi0DTO1.setOrderItemId(order0orderItem0Id);
		oi0DTO1.setTotalOrderedItemPrice(order0orderItem0amount * BookConstants.book0price);
		BookDTO b1DTO = new BookDTO(BookConstants.book0id, BookConstants.book0name, BookConstants.book0price,
				BookConstants.book0amount, BookConstants.book0deleted);
		AuthorDTO authPera = AuthorConstants.authorDTOs().getFirst();
		Set<AuthorDTO> authorDTOSet = new HashSet<AuthorDTO>();
		authorDTOSet.add(authPera);
		b1DTO.setAuthors(authorDTOSet);

		CategoryDTO catDTO = new CategoryDTO(CategoryConstants.category0id, CategoryConstants.category0name,
				CategoryConstants.category0isDeleted);
		Set<CategoryDTO> catDTOSet = new HashSet<>();
		catDTOSet.add(catDTO);
		b1DTO.setCategories(catDTOSet);
		oi0DTO1.setBookDTO(b1DTO);

		OrderItemDTO oi0DTO2 = new OrderItemDTO();
		oi0DTO2.setOrderId(order0orderId);
		oi0DTO2.setOrderedAmount(order0orderItem1amount);
		oi0DTO2.setOrderItemId(order0orderItem1Id);
		oi0DTO2.setTotalOrderedItemPrice(order0orderItem1amount * BookConstants.book1price);
		BookDTO b2DTO = new BookDTO(BookConstants.book1id, BookConstants.book1name, BookConstants.book1price,
				BookConstants.book1amount, BookConstants.book1deleted);
		b2DTO.setAuthors(authorDTOSet);
		b2DTO.setCategories(catDTOSet);
		oi0DTO2.setBookDTO(b2DTO);

		orderItemDTOList.add(oi0DTO1);
		orderItemDTOList.add(oi0DTO2);

		List<OrderDTO> orderDTOListNew = new ArrayList<>();
		OrderDTO oDTO0 = new OrderDTO(order0orderId, orderItemDTOList, order0Date, order0Price, userDTO);
		orderDTOListNew.add(oDTO0);

		return orderDTOListNew;
	}
	
	public static List<OrderItemDTO> getOrderItemDTOList() {
		LinkedList<OrderItemDTO> orderItemDTOList = new LinkedList<OrderItemDTO>();
		
		OrderItemDTO oi0DTO1 = new OrderItemDTO();
		BookDTO b1DTO = new BookDTO(BookConstants.book0id, BookConstants.book0name, BookConstants.book0price,
				BookConstants.book0amount, BookConstants.book0deleted);
		
		oi0DTO1.setOrderId(order0orderId);
		oi0DTO1.setOrderedAmount(order0orderItem0amount);
		oi0DTO1.setOrderItemId(order0orderItem0Id);
		oi0DTO1.setTotalOrderedItemPrice(order0orderItem0amount * BookConstants.book0price);		
		oi0DTO1.setBookDTO(b1DTO);
		orderItemDTOList.add(oi0DTO1);		
		
		return orderItemDTOList;
	}

}
