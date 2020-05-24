package com.codebrothers.services.auth.exceptions;

/*
 * Classe de exception customizada retornar Not Found
 */
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(Object id) {
		super("Resource not found. Id " + id);
	}
}
