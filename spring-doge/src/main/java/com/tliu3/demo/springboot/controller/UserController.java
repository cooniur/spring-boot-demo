package com.tliu3.demo.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tliu3.demo.springboot.domain.User;
import com.tliu3.demo.springboot.domain.UserRepository;
import com.tliu3.demo.springboot.exception.NotFoundException;

@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
	@Autowired
	UserRepository userRepository;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity getAll() {
		return ResponseEntity.ok(userRepository.findAll());
	}

	@RequestMapping(value = "{userId}", method = RequestMethod.GET)
	public ResponseEntity getUserById(@PathVariable Long userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new NotFoundException("User " + userId + " not found."));
		return ResponseEntity.ok(user);
	}
}
