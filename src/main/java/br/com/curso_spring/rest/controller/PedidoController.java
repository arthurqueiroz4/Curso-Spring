package br.com.curso_spring.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import static org.springframework.http.HttpStatus.*;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.curso_spring.domain.entity.ItemPedido;
import br.com.curso_spring.domain.entity.Pedido;
import br.com.curso_spring.rest.dto.InformacaoItemPedidoDTO;
import br.com.curso_spring.rest.dto.InformacoesPedidoDTO;
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

	@GetMapping("{id}")
	public InformacoesPedidoDTO getById(@PathVariable("id") Integer id) {
		return service
					.obterPedidoCompleto(id)
					.map(p -> converter(p))
					.orElseThrow(() -> {
						return new ResponseStatusException(NOT_FOUND, "Pedido não encontrado");
					});
	}

	private InformacoesPedidoDTO converter(Pedido p) {
		
		return InformacoesPedidoDTO
				.builder()
				.codigo(p.getId())
				.nomeCliente(p.getCliente().getNome())
				.cpf(p.getCliente().getCpf())
				.dataPedido(p.getDatapedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
				.total(p.getTotal())
				.status(p.getStatus().name())
				.items(converter(p.getItens()))
				.build();
	}
	
	private List<InformacaoItemPedidoDTO> converter (List<ItemPedido> itens){
		if (CollectionUtils.isEmpty(itens)) {
			return Collections.emptyList();
		}
		
		return itens.stream().map(
					item -> InformacaoItemPedidoDTO
								.builder()
								.precoUnitario(item.getProduto().getPreco())
								.quantidade(item.getQuantidade())
								.descricaoProduto(item.getProduto().getDescricao())
								.build()
				).collect(Collectors.toList());
	}
}
