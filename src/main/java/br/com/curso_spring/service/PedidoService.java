package br.com.curso_spring.service;

import java.util.Optional;

import br.com.curso_spring.domain.entity.Pedido;
import br.com.curso_spring.domain.entity.enums.StatusPedido;
import br.com.curso_spring.rest.dto.PedidoDTO;

public interface PedidoService {

	Pedido salvar(PedidoDTO dto);
	
	Optional<Pedido> obterPedidoService(Integer id);

	Optional<Pedido> obterPedidoCompleto(Integer id);
	
	void atualizarStatus(Integer id, StatusPedido statuspedido);
}
