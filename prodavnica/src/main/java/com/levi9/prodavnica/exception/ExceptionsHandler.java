package com.levi9.prodavnica.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionsHandler extends ResponseEntityExceptionHandler {

	private static final Logger log = LoggerFactory.getLogger(ExceptionsHandler.class);

	@ExceptionHandler(StoreException.class)
	public final ResponseEntity<?> handleApiError(StoreException ex, WebRequest request) {
		log.error("Error caught: " + ex.getMessage(), ex);
		return new ResponseEntity<>(ex, ex.getStatusCode());
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<?> handleAnyException(Exception ex) {
		log.error("Error caught: " + ex.getMessage(), ex);
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}