package com.example.demo.exception;

public class DoctorException extends IllegalArgumentException {

	public DoctorException(String message) {
		super(message);
	}

	public DoctorException(String message, Throwable cause) {
		super(message, cause);
	}
}
