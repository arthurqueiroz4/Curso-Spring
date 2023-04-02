package br.com.curso_spring.service;

import br.com.curso_spring.domain.entity.Pedido;
import br.com.curso_spring.rest.dto.PedidoDTO;

public interface PedidoService {

	public Pedido salvar(PedidoDTO dto);
}
