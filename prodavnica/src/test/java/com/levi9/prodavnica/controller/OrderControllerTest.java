package com.levi9.prodavnica.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.levi9.prodavnica.config.BookConstants;
import com.levi9.prodavnica.config.OrderConstants;
import com.levi9.prodavnica.config.UrlPrefix;
import com.levi9.prodavnica.dto.OrderReportDTO;
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
	
	public void getProcessedOrdersTest() throws Exception {
		OrderReportDTO orDTO = new OrderReportDTO();
		orDTO.setOrderDTOList(OrderConstants.orderDTOList);
		when(orderService.getOrderReport()).thenReturn(orDTO);
		
		mockMvc.perform(get(UrlPrefix.GET_ORDERS).accept(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.orderDTOList[0].orderId").value(OrderConstants.order0orderId))
			.andExpect(jsonPath("$.orderDTOList[0].orderDate").value(OrderConstants.order0Date))
			.andExpect(jsonPath("$.orderDTOList[0].orderPrice").value(OrderConstants.order0Price))
			.andExpect(jsonPath("$.orderDTOList[0].orderItemDTOList[0].orderItemId").value(OrderConstants.order0orderItem0Id))
			.andExpect(jsonPath("$.orderDTOList[0].orderItemDTOList[0].orderId").value(OrderConstants.order0orderId))
			.andExpect(jsonPath("$.orderDTOList[0].orderItemDTOList[0].orderedAmount").value(OrderConstants.order0orderItem0amount))
			.andExpect(jsonPath("$.orderDTOList[0].orderItemDTOList[0].totalOrderedItemPrice").value(OrderConstants.order0orderItem0amount * OrderConstants.order0Price))
			.andExpect(jsonPath("$.orderDTOList[0].orderItemDTOList[0].bookDTO.bookId").value(BookConstants.book0id))
			.andExpect(jsonPath("$.orderDTOList[0].orderItemDTOList[0].bookDTO.name").value(BookConstants.book0name))
			.andDo(document("{class-name}/{method-name}",preprocessResponse(prettyPrint()),
					responseFields(bookReportDTOResponseFields())));
				
	}
	
	 private FieldDescriptor[] bookReportDTOResponseFields() {
		    return new FieldDescriptor[] {
		    		fieldWithPath("orderDTOList.[].orderId").description("The unique identifier of the order"),
		    		fieldWithPath("orderDTOList.[].orderDate").description("Date when the order has been performed"),
		    		fieldWithPath("orderDTOList.[].orderPrice").description("Date when the order has been performed"),
		    		fieldWithPath("orderDTOList.[].orderItemDTOList.[].orderItemId").description("The unique identifier of the order item"),
		    		fieldWithPath("orderDTOList.[].orderItemDTOList.[].orderId").description("The unique identifier of the order"),
		    		fieldWithPath("orderDTOList.[].orderItemDTOList.[].orderedAmount").description("Amount of ordered item"),
		    		fieldWithPath("orderDTOList.[].orderItemDTOList.[].totalOrderedItemPrice").description("Total price of ordered items/books"),
		    		fieldWithPath("orderDTOList.[].orderItemDTOList.[].bookDTO.bookId").description("The unique identifier of the order item/book"),
		    		fieldWithPath("orderDTOList.[].orderItemDTOList.[].bookDTO.name").description("Name of the order item/book")		    		
		    };
		  }

	// TODO: lookup how to test InputStreamResource type
	public void getProcessedOrdersReportTest() throws Exception {
		
	}
	
}
