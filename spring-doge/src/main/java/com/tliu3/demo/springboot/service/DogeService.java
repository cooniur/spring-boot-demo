/*
 * Copyright 2012-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tliu3.demo.springboot.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tliu3.demo.springboot.domain.User;
import com.tliu3.demo.springboot.domain.UserPhoto;
import com.tliu3.demo.springboot.domain.UserRepository;

import doge.photo.Photo;
import doge.photo.PhotoManipulator;

@Service
public class DogeService {

	private final UserRepository userRepository;
	private final PhotoManipulator manipulator;

	@Autowired
	public DogeService(UserRepository userRepository, PhotoManipulator manipulator) {
		this.userRepository = userRepository;
		this.manipulator = manipulator;
	}

	public Photo getDogePhoto(final User user) throws IOException {
		return () -> {
			if (user.getPhoto().getPhoto() == null) {
				return new ByteArrayInputStream(new byte[] {});
			} else {
				return new ByteArrayInputStream(user.getPhoto().getPhoto());
			}
		};
	}

	public Photo addDogePhoto(User user, Photo photo) throws IOException {
		Photo manipulatedPhoto = this.manipulator.manipulate(photo);
		byte[] rawBytes = IOUtils.toByteArray(manipulatedPhoto.getInputStream());
		if (user.getPhoto() == null) {
			user.setPhoto(new UserPhoto(user, rawBytes));
		} else {
			user.getPhoto().setPhoto(rawBytes);
		}
		userRepository.saveAndFlush(user);
		return manipulatedPhoto;
	}
}
