package com.codebrothers.services.customer.service.handles;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.codebrothers.services.customer.entities.StandardError;
import com.codebrothers.services.customer.service.exceptions.DataBaseException;
import com.codebrothers.services.customer.service.exceptions.ResourceNotFoundException;
/*
 * Funciona muito parecido com um interceptor, mas tem outra funcionalidade real
 * sempre q houver uma exception com as classe envolvidas  essa classe entre em ação
 * devolvendo uma exception mais padronizada, evitando reescrita de código
 * ref: https://medium.com/@jovannypcg/understanding-springs-controlleradvice-cd96a364033f
 */
@ControllerAdvice
public class ResourceExceptionHandle {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
		String error = "Resource not found!";
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		StandardError standarError = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(status).body(standarError);
	}
	@ExceptionHandler(DataBaseException.class)
	public ResponseEntity<StandardError> dataBase(DataBaseException e, HttpServletRequest request) {
		String error = "Database error!";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		StandardError standarError = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(status).body(standarError);
	}
}
