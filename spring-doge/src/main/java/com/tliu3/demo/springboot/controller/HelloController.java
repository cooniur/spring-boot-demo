package com.tliu3.demo.springboot.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/hello")
public class HelloController {
	@RequestMapping
	public ResponseEntity hello(@RequestParam(value = "name", required = false, defaultValue = "") String name) {
		Map<String, Object> hello = new HashMap<>();
		if (name.isEmpty()) {
			hello.put("hello", "world");
		} else {
			hello.put("hello", name);
		}

		return ResponseEntity.ok(hello);
	}
}
