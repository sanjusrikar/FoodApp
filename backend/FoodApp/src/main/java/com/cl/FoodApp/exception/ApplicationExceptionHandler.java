package com.cl.FoodApp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cl.FoodApp.util.ResponseStructure;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> idNotFoundException(IdNotFoundException exception){
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage(exception.getMessage());
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setR("ID NOT FOUND");
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NotAuthorizedException.class)
	public ResponseEntity<ResponseStructure<String>> notAuthorizedException(NotAuthorizedException exception){
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage(exception.getMessage());
		structure.setStatus(HttpStatus.UNAUTHORIZED.value());
		structure.setR("ID NOT FOUND");
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.UNAUTHORIZED);
	}
	
}
