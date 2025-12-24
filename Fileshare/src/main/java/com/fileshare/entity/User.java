package com.fileshare.entity;

public class User {

	private String userName;
	private String passWord;

	public User(String userName, String passWord) {

		this.userName = userName;
		this.passWord = passWord;
	}

	public String getUsername() {
		return userName;
	}

	public String getPassword() {
		return passWord;
	}

}
