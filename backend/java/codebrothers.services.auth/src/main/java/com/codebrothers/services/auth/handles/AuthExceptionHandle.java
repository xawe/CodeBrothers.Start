package com.codebrothers.services.auth.handles;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.codebrothers.services.auth.entities.StandardError;
import com.codebrothers.services.auth.exceptions.DataBaseException;
import com.codebrothers.services.auth.exceptions.ResourceForbiddenException;
import com.codebrothers.services.auth.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class AuthExceptionHandle  {
    public Logger log  = LogManager.getLogger(this);
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        String error = "Resource not found!";
        HttpStatus status = HttpStatus.NOT_FOUND;

        log.error("Erro - {} - Stacktrace{}", e.getMessage(), e.getStackTrace());
        
        return ResponseEntity.status(status)
                .body(new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI()));
    }

    @ExceptionHandler(DataBaseException.class)
    public ResponseEntity<StandardError> dataBase(DataBaseException e, HttpServletRequest request) {
        String error = "Database error!";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        
        log.error("Erro - {} - Stacktrace{}", e.getMessage(), e.getStackTrace());
        
        return ResponseEntity.status(status)
                .body(new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI()));
    }

    @ExceptionHandler(ResourceForbiddenException.class)
    public ResponseEntity<StandardError> resourceForbiddenException(ResourceForbiddenException  e, HttpServletRequest request) {
        String error = "Database error!";
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        
        log.error("Erro - {} - Stacktrace{}", e.getMessage(), e.getStackTrace());
        
        return ResponseEntity.status(status)
                .body(new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI()));
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> dataBase(MethodArgumentNotValidException e, HttpServletRequest request) {
        String error = "Bad request!";
        HttpStatus status = HttpStatus.BAD_REQUEST;

        log.error("Erro - {} - Stacktrace{}", e.getMessage(), e.getStackTrace());
        
        return ResponseEntity.status(status)
                .body(new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI()));
    }
}
