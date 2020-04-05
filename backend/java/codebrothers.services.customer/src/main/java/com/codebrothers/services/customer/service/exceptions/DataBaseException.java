package com.codebrothers.services.customer.service.exceptions;
/*
 * Classe de exception customizada para o banco
 */
public class DataBaseException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DataBaseException(String msg) {
		super(msg);
	}
}
