package com.levi9.prodavnica.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Set;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.levi9.prodavnica.serviceImpl.CustomUserDetailsService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.levi9.prodavnica.config.OrderConstants;
import com.levi9.prodavnica.dto.OrderDTO;
import com.levi9.prodavnica.dto.OrderReportDTO;
import com.levi9.prodavnica.dto.OrderResponseDTO;
import com.levi9.prodavnica.exception.StoreException;
import com.levi9.prodavnica.mapper.BookMapper;
import com.levi9.prodavnica.model.Book;
import com.levi9.prodavnica.model.Order;
import com.levi9.prodavnica.model.OrderItem;
import com.levi9.prodavnica.repository.BookRepository;
import com.levi9.prodavnica.repository.OrderRepository;
import com.levi9.prodavnica.serviceImpl.OrderServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(OrderServiceImpl.class)
public class OrderServiceImplTest {

	@MockBean
	CustomUserDetailsService userDetailsService;

	@Autowired
	MockMvc mockMvc;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@MockBean
	OrderRepository orderRepository;

	@MockBean
	BookRepository bookRepository;

	@MockBean
	UserRepository userRepository;

	@Autowired
	OrderService orderService;

	@Autowired
	ObjectMapper objectMapper;

	@MockBean
	BookMapper bookMapper;

	@Test
	public void whenCreateOrder_returnSuccess() throws Exception {
		when(bookRepository.getOne(any())).thenReturn(OrderConstants.bookResponseExpected);

		ArgumentCaptor<Order> captor = ArgumentCaptor.forClass(Order.class);
		when(orderRepository.save(any())).thenReturn(OrderConstants.orderResponseExpected);

		when(userRepository.findOneByUsername(any())).thenReturn(new User());

		OrderResponseDTO responseReturned = orderService.addOrder(OrderConstants.orderRequest, "test");
		verify(orderRepository).save(captor.capture());

		Order orderRequestActual = captor.getValue();
		Set<OrderItem> orderItems = orderRequestActual.getOrderItems();

		OrderItem orderItemRequestActual = new OrderItem();
		for (OrderItem order : orderItems) {
			orderItemRequestActual.setAmount(order.getAmount());
			orderItemRequestActual.setBook(order.getBook());
		}

		// Verify request fields
		assertEquals(OrderConstants.order0total, orderRequestActual.getTotal(), 0.001);
		assertEquals(OrderConstants.order0amount, orderItemRequestActual.getAmount(), 0.001);
		assertEquals(OrderConstants.order0bookId, orderItemRequestActual.getBook().getBookId(), 0.001);

		// Verify response fields
		assertEquals(OrderConstants.order0orderId, responseReturned.getOrderId());
	}

	@Test
	public void given_orderServiceThrowsAnError_requestIsEmpty_throwAnError() throws Exception {
		when(bookRepository.getOne(any())).thenReturn(null);
		thrown.expect(StoreException.class);

		orderService.addOrder(OrderConstants.orderListNull, "test");
	}

	@Test
	public void given_orderServiceThrowsAnError_whenGetBook_throwAnError() throws Exception {
		when(bookRepository.getOne(any())).thenReturn(null);
		thrown.expect(StoreException.class);

		orderService.addOrder(OrderConstants.orderListDTO, "test");
	}

	@Test
	public void given_orderServiceThrowsAnError_whenAmountIsGreaterThanOnStock_throwAnError() throws Exception {
		when(bookRepository.getOne(any())).thenReturn(new Book(1L, "test", 1, 1, false));
		thrown.expect(StoreException.class);

		orderService.addOrder(OrderConstants.orderListDTO, "test");
	}

	@Test
	public void getOrderReportTest_success() throws Exception {
		when(orderRepository.findAll()).thenReturn(OrderConstants.getAllOrders());

		OrderReportDTO reportDto = orderService.getOrderReport();
		List<OrderDTO> orderDTOList = reportDto.getOrderDTOList();
		assertEquals(orderDTOList.get(0).getOrderId(), OrderConstants.order0orderId);

		assertEquals(orderDTOList.get(0).getOrderDate(), OrderConstants.order0Date);
		assertEquals(orderDTOList.get(0).getOrderItemDTOList().get(0).getOrderItemId(),
				OrderConstants.getOrderItemDTOList().get(0).getOrderItemId(), 0.001);
		assertEquals(orderDTOList.get(0).getOrderPrice(), OrderConstants.order0Price, 0.001);
	}

	@Test
	public void getOrderReportTest_throwException() throws Exception {
		when(orderRepository.findAll()).thenReturn(OrderConstants.getAllOrders_withNullDate());
		thrown.expect(StoreException.class);
		orderService.getOrderReport();
	}

}
