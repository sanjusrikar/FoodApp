package com.cl.FoodApp.exception;

public class NotAuthorizedException extends RuntimeException {
	String message = "USER NOT AUTHORIZED";
	
	@Override
	public String getMessage() {
		return message;
	}

}
