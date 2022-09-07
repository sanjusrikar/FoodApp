package com.cl.FoodApp.util;

public class ResponseStructure<R> {
	
	private String message;
	private int status;
	R r;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public R getR() {
		return r;
	}
	public void setR(R r) {
		this.r = r;
	}
	

}
