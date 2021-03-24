package com.tellme.demo.users;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "users")
public class User {


	@Id
	private String name;
	private String password;
	private String mobile;
	private String email;
	private String userType;

	public User() {
	}

	public User(String name, String password, String mobile, String email, String userType) {
		this.name = name;
		this.password = password;
		this.mobile = mobile;
		this.email = email;
		this.userType = userType;
	}

	@Override
	public String toString() {
		return "{"
				+ "  \"name\":\"" + name + "\""
				+ ",  \"password\":\"" + password + "\""
				+ ",  \"mobile\":\"" + mobile + "\""
				+ ",  \"email\":\"" + email + "\""
				+ ",  \"userType\":\"" + userType + "\""
				+ "}}";
	}
}