package br.com.curso_spring.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.http.HttpStatus.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.curso_spring.domain.entity.Pedido;
import br.com.curso_spring.rest.dto.PedidoDTO;
import br.com.curso_spring.service.PedidoService;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
	
	@Autowired
	private PedidoService service;

	@PostMapping
	@ResponseStatus(CREATED)
	public Integer save (@RequestBody PedidoDTO dto) {
		Pedido pedido = service.salvar(dto);
		
		return pedido.getId();
	}
}
