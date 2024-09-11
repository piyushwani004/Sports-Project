package com.piyush004.SportsApi.Exception;

import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ErrorMessage resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
		ErrorMessage message = new ErrorMessage();
		message.setStatusCode(HttpStatus.NOT_FOUND.value());
		message.setDescription(request.getDescription(false));
		message.setMessage(ex.getMessage());
		message.setTimestamp(new Date());
		return message;
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorMessage globalExceptionHandler(Exception ex, WebRequest request) {
		ErrorMessage message = new ErrorMessage();
		message.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		message.setDescription(request.getDescription(false));
		message.setMessage(ex.getMessage());
		message.setTimestamp(new Date());

		return message;
	}
}