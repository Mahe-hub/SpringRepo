package com.fdmgroup.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmployeeNotFoundException extends RuntimeException {

	public EmployeeNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	
}
