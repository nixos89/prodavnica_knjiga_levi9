package com.levi9.prodavnica.serviceImpl;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import com.levi9.prodavnica.model.User;
import com.levi9.prodavnica.repository.UserRepository;
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
	private  OrderRepository orderRepository;
	@Autowired
	private  BookRepository bookRepository;
	@Autowired
	private  UserRepository userRepository;

	@Override
	public OrderResponseDTO addOrder(OrderListDTO orderRequest) {
 
		Set<OrderItem> orderItems = new HashSet<>();
		Order order = new Order();
		if (orderRequest != null) {
			for (AddOrderDTO addOrder : orderRequest.getOrders()) {

				Book book = bookRepository.getOne(addOrder.getBookId());
				if (book == null)
					throw new StoreException(HttpStatus.BAD_REQUEST, "Book doesn't exist!");
				else if (addOrder.getAmount() > book.getAmount())
					throw new StoreException(HttpStatus.BAD_REQUEST, "Amount for book with title: '" + book.getName() + 
							"' is more than on the stock!\nCurrent amount on stock is: " + book.getAmount());
				else {
					if(userRepository.findOneByUsername(orderRequest.getUsername())==null)
						throw new StoreException(HttpStatus.NOT_FOUND,"User not found");
					book.setAmount(book.getAmount() - addOrder.getAmount());
					order.setTotal(orderRequest.getTotal());
					order.setOrderDate(new Timestamp(System.currentTimeMillis()));
					order.setUser(userRepository.findOneByUsername(orderRequest.getUsername()));
					OrderItem orderItem = new OrderItem();
					orderItem.setAmount(addOrder.getAmount());
					orderItem.setBook(book);
					orderItem.setOrder(order);

					order.setOrderItems(orderItems);

					orderItems.add(orderItem);

					order = orderRepository.save(order);
				}
			}
		} else {
			throw new StoreException(HttpStatus.BAD_REQUEST, "Empty request!");
		}

		return new OrderResponseDTO(order.getOrderId());
	}

}
