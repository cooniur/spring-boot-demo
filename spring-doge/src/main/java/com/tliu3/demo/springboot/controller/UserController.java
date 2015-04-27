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

@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
	@Autowired
	UserRepository userRepository;

	@RequestMapping(value = "{userId}", method = RequestMethod.GET)
	public ResponseEntity getUserById(@PathVariable Long userId) {
		User user = userRepository.findOne(userId);
		if (user == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(user);
		}
	}
}
