package com.pathwheel.exception;

import com.pathwheel.response.Codes;

public class AuthenticationFailureException extends Exception {
	
	private static final long serialVersionUID = 286067498797258567L;
	
	private final int code = Codes.UNAUTHORIZED;

	public AuthenticationFailureException() {
		super("Authentication failure");
	}

	public int getCode() {
		return code;
	}
		
}
