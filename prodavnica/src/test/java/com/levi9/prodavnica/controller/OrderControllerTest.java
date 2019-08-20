package com.levi9.prodavnica.controller;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.levi9.prodavnica.serviceImpl.CustomUserDetailsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
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

	String TOKEN_ATTR_NAME = "org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository.CSRF_TOKEN";
	HttpSessionCsrfTokenRepository httpSessionCsrfTokenRepository = new HttpSessionCsrfTokenRepository();
	CsrfToken csrfToken = httpSessionCsrfTokenRepository.generateToken(new MockHttpServletRequest());

	@MockBean
	CustomUserDetailsService userDetailsService;

	@Autowired
	MockMvc mockMvc;

	@MockBean
	OrderService orderService;

	@Autowired
	ObjectMapper objectMapper;

	@Test
	@WithMockUser
	public void addOrder() throws Exception {
		when(orderService.addOrder(any())).thenReturn(new OrderResponseDTO(OrderConstants.order0orderId));
		mockMvc.perform(post(UrlPrefix.GET_ORDERS).accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(OrderConstants.orderListDTO)).sessionAttr(TOKEN_ATTR_NAME, csrfToken)
				.param(csrfToken.getParameterName(), csrfToken.getToken())).andExpect(status().isOk()).andDo(print())
				.andExpect(jsonPath("$.orderId").value(OrderConstants.order0orderId))
				.andDo(document("{class-name}/{method-name}"));
	}

}
