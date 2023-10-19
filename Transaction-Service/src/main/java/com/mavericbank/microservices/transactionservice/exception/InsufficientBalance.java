package com.mavericbank.microservices.transactionservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InsufficientBalance extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InsufficientBalance(String string) {
		super(string);
	}
}
