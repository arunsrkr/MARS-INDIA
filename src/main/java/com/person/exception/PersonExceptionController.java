package com.person.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PersonExceptionController {
	@ExceptionHandler(value=Exception.class)
	public ResponseEntity<String> exception(PersonException exception){
		System.out.println(exception.getMsg());
		return new ResponseEntity<>(exception.getMsg(),HttpStatus.BAD_REQUEST);
	}
	
	

}
