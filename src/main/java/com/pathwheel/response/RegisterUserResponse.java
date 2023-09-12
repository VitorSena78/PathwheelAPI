package com.pathwheel.response;

import com.pathwheel.model.User;

public class RegisterUserResponse extends Response {
	private User user;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "AuthenticateResponse [user=" + user + ", getCode()=" + getCode() + ", getDescription()="
				+ getDescription() + "]";
	}
	
	
}
