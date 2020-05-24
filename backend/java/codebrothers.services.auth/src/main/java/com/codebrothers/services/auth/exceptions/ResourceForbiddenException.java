package com.codebrothers.services.auth.exceptions;

/*
 * Classe de exception customizada retornar Not Found
 */
public class ResourceForbiddenException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ResourceForbiddenException(String msg) {
		super(msg);
	}
}
