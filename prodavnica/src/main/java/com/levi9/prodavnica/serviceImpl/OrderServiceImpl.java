package com.levi9.prodavnica.serviceImpl;

import com.levi9.prodavnica.dto.*;
import com.levi9.prodavnica.exception.StoreException;
import com.levi9.prodavnica.mapper.BookMapper;
import com.levi9.prodavnica.model.Book;
import com.levi9.prodavnica.model.Order;
import com.levi9.prodavnica.model.OrderItem;
import com.levi9.prodavnica.repository.BookRepository;
import com.levi9.prodavnica.repository.OrderRepository;
import com.levi9.prodavnica.repository.UserRepository;
import com.levi9.prodavnica.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired
	private  OrderRepository orderRepository;
	@Autowired
	private  BookRepository bookRepository;

	@Autowired
	public BookMapper bookMaper;

	@Autowired
	private  UserRepository userRepository;

	@Override
	public OrderResponseDTO addOrder(OrderListDTO orderRequest, String username) {
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
					if(userRepository.findOneByUsername(username)==null)
						throw new StoreException(HttpStatus.NOT_FOUND,"User not found");
					book.setAmount(book.getAmount() - addOrder.getAmount());
					order.setTotal(orderRequest.getTotal());
					order.setOrderDate(new Timestamp(System.currentTimeMillis()));
					order.setUser(userRepository.findOneByUsername(username));
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

	@Override
	public OrderReportDTO getOrderReport() {

		List<OrderItemDTO> orderItemDTOList = new LinkedList<OrderItemDTO>();
		List<OrderDTO> orderDTOList = new LinkedList<OrderDTO>();

		List<Order> orders = orderRepository.findAll();
		OrderDTO orderDTO = new OrderDTO();
		double orderPrice;
		for (Order order : orders) {
			orderDTO = new OrderDTO();
			orderDTO.setOrderId(order.getOrderId());
			orderDTO.setUser(new UserDTO(order.getUser()));
			Date orderDate = order.getOrderDate();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			String dateString = null;

			if(orderDate == null) {
				throw new StoreException(HttpStatus.BAD_REQUEST, "Error, date formatting failed!");
			}
			dateString = sdf.format(orderDate);
			orderDTO.setOrderDate(dateString);


			orderPrice = 0.0;
			for (OrderItem oi : order.getOrderItems()) {
				OrderItemDTO oiDTO = new OrderItemDTO();
				BookDTO bookDTO = bookMaper.map(oi.getBook());

				oiDTO.setOrderItemId(oi.getOrderItemId());
				oiDTO.setOrderId(order.getOrderId());
				oiDTO.setBookDTO(bookDTO);
				oiDTO.setOrderedAmount(oi.getAmount());

				BigDecimal bd = new BigDecimal((double) (oi.getBook().getPrice() * oi.getAmount())).setScale(2, RoundingMode.HALF_UP);
				double booksPriceNew = bd.doubleValue();
				oiDTO.setTotalOrderedItemPrice(booksPriceNew);
				orderPrice += booksPriceNew;
				orderItemDTOList.add(oiDTO);
			}
			BigDecimal bd2 = new BigDecimal(orderPrice).setScale(2, RoundingMode.HALF_UP);
			double orderPriceNew = bd2.doubleValue();
			orderDTO.setOrderPrice(orderPriceNew);
			orderDTO.setOrderItemDTOList(orderItemDTOList);
			orderDTOList.add(orderDTO);
			orderItemDTOList = new LinkedList<OrderItemDTO>();
		}

		OrderReportDTO orderReportDTO = new OrderReportDTO();
		orderReportDTO.setOrderDTOList(orderDTOList);
		return orderReportDTO;
	}

}
