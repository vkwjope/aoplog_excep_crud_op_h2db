package com.example.employeemanagementservice.exception.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.employeemanagementservice.exceptions.EmployeeAlreadyExistsException;
import com.example.employeemanagementservice.exceptions.EmployeeNotFoundException;
import com.example.employeemanagementservice.exceptions.ExceptionResponse;

@ControllerAdvice
public class EmployeeExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(EmployeeNotFoundException.class)
	public final ResponseEntity<java.lang.Object> handleEmployeeNotFoundException(java.lang.Exception ex,
			WebRequest request) {
		HttpStatus httpStatus = HttpStatus.NOT_FOUND;
		ExceptionResponse exceptionResponse = ExceptionResponse.builder().timestamp(new Date())
				.status(httpStatus.value()).error(httpStatus.getReasonPhrase()).message(ex.getMessage())
				.details(request.getDescription(false)).build();
		return new ResponseEntity<>(exceptionResponse, httpStatus);
	}

	@ExceptionHandler(EmployeeAlreadyExistsException.class)
	public ResponseEntity<Object> handleEmployeeAlreadyExistsException(Exception ex, WebRequest req) {
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		ExceptionResponse exceptionResponse = ExceptionResponse.builder().timestamp(new Date())
				.status(httpStatus.value()).error(httpStatus.getReasonPhrase()).message(ex.getMessage())
				.details(req.getDescription(false)).build();

		return new ResponseEntity<>(exceptionResponse, httpStatus);

	}

}
