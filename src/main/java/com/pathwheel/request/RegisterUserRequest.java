package com.pathwheel.request;

import com.pathwheel.model.Spot;
import com.pathwheel.model.User;

public class RegisterUserRequest extends Request {
	private User user;

	private String secret;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	@Override
	public String toString() {
		return "RegisterUserRequest [user: "+user+", secret: "+secret+"]";
	}
	
}
