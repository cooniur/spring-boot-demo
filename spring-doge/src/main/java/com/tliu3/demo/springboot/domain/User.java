package com.tliu3.demo.springboot.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User extends BaseEntity {
	@Column
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToOne(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER, mappedBy = "user")
	public UserPhoto getPhoto() {
		return photo;
	}

	public void setPhoto(UserPhoto photo) {
		this.photo = photo;
	}

	public User() {}

	public User(String name) {
		this.name = name;
	}

	public User(String name, UserPhoto photo) {
		this(name);
		this.photo = photo;
	}

	private String name;
	private UserPhoto photo;

	@Override
	public String toString() {
		return "User{" +
				"id='" + id + "', " +
				"name='" + name + "'" +
				'}';
	}
}
