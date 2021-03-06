package br.com.coretecnologia.cursomc.services.exceptions;

public class AuthorizationException extends RuntimeException {

	private static final long serialVersionUID = -5710324911861792926L;
	
	public AuthorizationException(String message) {
		super(message);
	}
	
	public AuthorizationException(String message, Throwable cause) {
		super(message, cause);
	}

}
