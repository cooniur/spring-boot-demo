package com.tliu3.demo.springboot.exception;

public class NotFoundException extends RuntimeException {
	public NotFoundException(String message) {
		super(message);
	}

	public NotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	@Override
	public synchronized Throwable fillInStackTrace() {
		return this;
	}
}
