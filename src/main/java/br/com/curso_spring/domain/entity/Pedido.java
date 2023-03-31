package br.com.curso_spring.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Pedido {

	private Integer id;
	private Cliente cliente;
	private LocalDate datapedido;
	private BigDecimal total;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public LocalDate getDatapedido() {
		return datapedido;
	}
	public void setDatapedido(LocalDate datapedido) {
		this.datapedido = datapedido;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	
	
}
