package com.mavericbank.microservices.transactionservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidTransactionType extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidTransactionType(String message) {
		super(message);
	}
}
