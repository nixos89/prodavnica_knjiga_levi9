package com.levi9.prodavnica.serviceImpl;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.levi9.prodavnica.dto.AddOrderDTO;
import com.levi9.prodavnica.dto.OrderListDTO;
import com.levi9.prodavnica.dto.OrderResponseDTO;
import com.levi9.prodavnica.exception.StoreException;
import com.levi9.prodavnica.model.Book;
import com.levi9.prodavnica.model.Order;
import com.levi9.prodavnica.model.OrderItem;
import com.levi9.prodavnica.repository.BookRepository;
import com.levi9.prodavnica.repository.OrderRepository;
import com.levi9.prodavnica.service.OrderService;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRepository orderRepository;
	@Autowired
	BookRepository bookRepository;

	@Override
	public OrderResponseDTO addOrder(OrderListDTO orderRequest) {

		Set<OrderItem> orderItems = new HashSet<>();
		Order order = new Order();
		for (AddOrderDTO addOrder : orderRequest.getOrders()) {
			Book book = bookRepository.getOne(addOrder.getBookId());
			if (book == null)
				throw new StoreException(HttpStatus.INTERNAL_SERVER_ERROR, "Book doesn't exist!");
			else if (addOrder.getAmount() > book.getAmount())
				throw new StoreException(HttpStatus.INTERNAL_SERVER_ERROR, "Amount is more than on the stock!");
			else {
				book.setAmount(book.getAmount() - addOrder.getAmount());
				order.setTotal(orderRequest.getTotal());
				order.setOrderDate(new Timestamp(System.currentTimeMillis()));

				OrderItem orderItem = new OrderItem();
				orderItem.setAmount(addOrder.getAmount());
				orderItem.setBook(book);
				orderItem.setOrder(order);

				order.setOrderItems(orderItems);

				orderItems.add(orderItem);

				orderRepository.save(order);
			}
		}

		return new OrderResponseDTO(order.getOrderId());
	}

}
