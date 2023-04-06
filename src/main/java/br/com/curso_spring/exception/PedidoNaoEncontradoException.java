package br.com.curso_spring.exception;

public class PedidoNaoEncontradoException extends RuntimeException{

	public PedidoNaoEncontradoException() {
		super("Pedido não encontrado");
	}
}
	