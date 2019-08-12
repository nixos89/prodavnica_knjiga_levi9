package com.levi9.prodavnica.service;

import com.levi9.prodavnica.dto.OrderListDTO;
import com.levi9.prodavnica.dto.OrderResponseDTO;

public interface OrderService {

	OrderResponseDTO addOrder(OrderListDTO orderRequest);

}
