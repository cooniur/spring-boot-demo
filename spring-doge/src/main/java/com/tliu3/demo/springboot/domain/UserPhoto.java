package com.tliu3.demo.springboot.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "user_photo")
public class UserPhoto extends BaseEntity {
	@Column
	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "user_id")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserPhoto() {}

	public UserPhoto(User user, byte[] rawBytes) {
		this.user = user;
		this.photo = rawBytes;
	}

	private User user;
	private byte[] photo;
}
