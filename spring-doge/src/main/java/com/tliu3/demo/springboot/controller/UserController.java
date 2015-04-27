package com.tliu3.demo.springboot.controller;

import java.io.IOException;
import java.net.URI;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import com.tliu3.demo.springboot.domain.User;
import com.tliu3.demo.springboot.domain.UserRepository;
import com.tliu3.demo.springboot.exception.NotFoundException;
import com.tliu3.demo.springboot.service.DogeService;

import doge.photo.Photo;
import doge.photo.PhotoResource;

@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
	private final UserRepository userRepository;
	private final DogeService dogeService;

	@Autowired
	public UserController(UserRepository userRepository, DogeService dogeService) {
		this.userRepository = userRepository;
		this.dogeService = dogeService;
	}

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

	@RequestMapping(value = "{userId}/doge", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Resource getDogePhoto(@PathVariable Long userId) throws IOException {
		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new NotFoundException("User " + userId + " not found."));

		Photo photo = this.dogeService.getDogePhoto(user);
		return new PhotoResource(photo);
	}

	@RequestMapping(value = "{userId}/doge", method = RequestMethod.POST)
	public ResponseEntity postDogePhoto(@PathVariable Long userId, @RequestParam MultipartFile file,
			UriComponentsBuilder uriBuilder, HttpServletRequest request) throws IOException {
		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new NotFoundException("User " + userId + " not found."));

		Photo photo = file::getInputStream;
		this.dogeService.addDogePhoto(user, photo);
		URI uri = uriBuilder.path("/user/{userId}/doge")
				.buildAndExpand(userId).toUri();
		return ResponseEntity.created(uri).build();
	}
}
