package com.levi9.prodavnica.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoreException extends RuntimeException {

	private static final long serialVersionUID = 8015647556248899491L;

	private HttpStatus statusCode;
	private String message;

	public StoreException(HttpStatus statusCode, String message) {
		this.statusCode = statusCode;
		this.message = message;
	}

}
