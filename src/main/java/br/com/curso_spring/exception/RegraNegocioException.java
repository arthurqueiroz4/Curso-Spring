package br.com.curso_spring.exception;

public class RegraNegocioException extends RuntimeException{

	public RegraNegocioException(String message) {
		super(message);
	}

}
