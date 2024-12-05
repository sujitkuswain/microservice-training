package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(DoctorAlreadyExistsException.class)
	public ResponseEntity<String> handleCustomerException(DoctorAlreadyExistsException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
	}

}
