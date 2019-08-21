package com.levi9.prodavnica.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.levi9.prodavnica.dto.OrderListDTO;
import com.levi9.prodavnica.dto.OrderReportDTO;
import com.levi9.prodavnica.service.OrderService;
import com.levi9.prodavnica.utils.PDFGenerator;

@RestController
@RequestMapping("api/orders")
@CrossOrigin(origins = "*")
public class OrderController {

	@Autowired
	OrderService orderService;

	@PostMapping
	public ResponseEntity<?> addOrder(@RequestBody OrderListDTO orderRequest) {
		return ResponseEntity.ok(orderService.addOrder(orderRequest));
	}

	@GetMapping()
	public ResponseEntity<?> getProcessedOrders(){
		return ResponseEntity.ok(orderService.getOrderReport());
	}
	
	@GetMapping("/pdf")
	public ResponseEntity<InputStreamResource> orderReportPdf() throws IOException {
		OrderReportDTO orderReportDTO = orderService.getOrderReport();
		ByteArrayInputStream bis = PDFGenerator.ordersPDFReport(orderReportDTO);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=orders.pdf");
		
		return ResponseEntity.ok()
				.headers(headers)
				.contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));
	}
}
