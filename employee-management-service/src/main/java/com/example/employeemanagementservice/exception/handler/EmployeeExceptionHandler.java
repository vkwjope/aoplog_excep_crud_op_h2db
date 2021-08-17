package com.example.employeemanagementservice.exception.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.employeemanagementservice.exceptions.EmployeeNotFoundException;
import com.example.employeemanagementservice.exceptions.ExceptionResponse;

@ControllerAdvice
public class EmployeeExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(EmployeeNotFoundException.class)
	public final ResponseEntity<java.lang.Object> handleEmployeeNotFoundException(java.lang.Exception ex,
			WebRequest request) {
		HttpStatus httpStatus = HttpStatus.NOT_FOUND;
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), httpStatus.value(),
				httpStatus.getReasonPhrase(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, httpStatus);
	}

}
