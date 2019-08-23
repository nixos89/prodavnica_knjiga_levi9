package com.levi9.prodavnica.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
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

import java.security.Principal;

@RestController
@RequestMapping("api/orders")
@CrossOrigin(origins = "*")
public class OrderController {

	@Autowired
	OrderService orderService;

	@PreAuthorize(value = "hasAuthority('USER') or hasRole('USER')")
	@PostMapping
	public ResponseEntity<?> addOrder(@RequestBody @Validated OrderListDTO orderRequest, Principal principal) {
		return ResponseEntity.ok(orderService.addOrder(orderRequest, principal.getName()));
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
		
		System.out.println(" ====== I orderReportPdf() have been summoned at " + LocalDateTime.now() + " ! ======== ");
		
		return ResponseEntity.ok()
				.headers(headers)
				.contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));
	}
}
