package com.levi9.prodavnica.controller;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.levi9.prodavnica.config.OrderConstants;
import com.levi9.prodavnica.config.UrlPrefix;
import com.levi9.prodavnica.dto.OrderResponseDTO;
import com.levi9.prodavnica.service.OrderService;

@RunWith(SpringRunner.class)
@WebMvcTest(OrderController.class)
@AutoConfigureRestDocs(outputDir = "target/generated-sources/snippets")
public class OrderControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	OrderService orderService;

	@Autowired
	ObjectMapper objectMapper;

	@Test
	public void addOrder() throws Exception {
		when(orderService.addOrder(any())).thenReturn(new OrderResponseDTO(OrderConstants.order0orderId));
		mockMvc.perform(post(UrlPrefix.GET_ORDERS).accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(OrderConstants.orderListDTO))).andExpect(status().isOk()).andDo(print())
				.andExpect(jsonPath("$.orderId").value(OrderConstants.order0orderId))
				.andDo(document("{class-name}/{method-name}"));
	}

}
