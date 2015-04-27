package com.tliu3.demo.springboot.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.tliu3.demo.springboot.exception.NotFoundException;

@ControllerAdvice
public class GlobalExceptionHandlerController {
	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ResponseEntity notFoundException(NotFoundException e) {
		Map<String, Object> body = new HashMap<>();
		body.put("message", e.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
	}
}
