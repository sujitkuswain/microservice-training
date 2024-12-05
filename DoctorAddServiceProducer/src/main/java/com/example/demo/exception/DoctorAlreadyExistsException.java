package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DoctorAlreadyExistsException extends RuntimeException {

	private String message;

	public DoctorAlreadyExistsException() {
		this("already exists");
	}

	public DoctorAlreadyExistsException(String message) {
		super(message);
	}
}
