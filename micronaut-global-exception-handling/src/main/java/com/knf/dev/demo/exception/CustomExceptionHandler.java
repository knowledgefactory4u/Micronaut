package com.knf.dev.demo.exception;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;

@Produces
@Singleton
@Requires(classes = { CustomException.class, ExceptionHandler.class })

public class CustomExceptionHandler 
    implements ExceptionHandler<CustomException, HttpResponse<ErrorMessage>> {

	@Override
	public HttpResponse<ErrorMessage> 
	    handle(HttpRequest request, CustomException exception) {

		ErrorMessage message = new ErrorMessage();
		message.setMessage(exception.getMessage());
		message.setStatus(false);
		return HttpResponse.serverError(message).
				status(HttpStatus.BAD_REQUEST);
	}

}
