package com.cl.FoodApp.exception;

public class IdNotFoundException extends RuntimeException {
	private String message= "ID NOT FOUND";
	
	@Override
	public String getMessage() {
		return message;
	}

}
