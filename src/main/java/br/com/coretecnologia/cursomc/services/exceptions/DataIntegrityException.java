package br.com.coretecnologia.cursomc.services.exceptions;

public class DataIntegrityException extends RuntimeException {

	private static final long serialVersionUID = -5710324911861792926L;
	
	public DataIntegrityException(String message) {
		super(message);
	}
	
	public DataIntegrityException(String message, Throwable cause) {
		super(message, cause);
	}

}
