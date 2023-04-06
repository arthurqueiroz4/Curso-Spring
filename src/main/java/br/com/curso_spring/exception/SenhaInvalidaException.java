package br.com.curso_spring.exception;

public class SenhaInvalidaException extends RuntimeException {
	public SenhaInvalidaException() {
		super("Senha inválida");
	}
}
