package com.orangetalents.simpleApiToBank.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.orangetalents.simpleApiToBank.services.exceptions.DatabaseException;
import com.orangetalents.simpleApiToBank.services.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException rnfe, HttpServletRequest request) {
		
		String error = "Resource not found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError sError = new StandardError(Instant.now(), status.value(), error, rnfe.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(status).body(sError);
	}
	
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandardError> database(DatabaseException rnfe, HttpServletRequest request) {
		
		String error = "Data base error";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError sError = new StandardError(Instant.now(), status.value(), error, rnfe.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(status).body(sError);
	}
}
