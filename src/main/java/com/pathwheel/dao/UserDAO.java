package com.pathwheel.dao;

import com.pathwheel.exception.AuthenticationFailureException;
import com.pathwheel.exception.UserNotFoundException;
import com.pathwheel.model.User;

public interface UserDAO {

	User authenticate(String login, String secret) throws UserNotFoundException, AuthenticationFailureException;
	User register(User user, String secret);
}
