package br.com.coretecnologia.cursomc.services.exceptions;

public class FileException extends RuntimeException {

	private static final long serialVersionUID = -6626249590432518220L;

	public FileException(String message) {
		super(message);
	}
	
	public FileException(String message, Throwable cause) {
		super(message, cause);
	}

}
