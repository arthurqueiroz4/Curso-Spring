package br.com.curso_spring.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.curso_spring.domain.entity.ItemPedido;
import br.com.curso_spring.domain.entity.Pedido;
import br.com.curso_spring.domain.entity.Produto;
import br.com.curso_spring.domain.entity.enums.StatusPedido;
import br.com.curso_spring.domain.repository.Clientes;
import br.com.curso_spring.domain.repository.ItensPedidos;
import br.com.curso_spring.domain.repository.Pedidos;
import br.com.curso_spring.domain.repository.Produtos;
import br.com.curso_spring.exception.RegraNegocioException;
import br.com.curso_spring.rest.dto.InformacaoItemPedidoDTO;
import br.com.curso_spring.rest.dto.ItemPedidoDTO;
import br.com.curso_spring.rest.dto.PedidoDTO;
import br.com.curso_spring.service.PedidoService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService{

	private final Pedidos pedidosRepository;
	private final Clientes clientesRepository;
	private final Produtos produtosRepository;
	private final ItensPedidos itemsPedidoRepository;

	@Transactional
	@Override
	public Pedido salvar(PedidoDTO dto) {
		Pedido pedido = new Pedido();
		pedido.setTotal(dto.getTotal());
		pedido.setDatapedido(LocalDate.now());
		pedido.setStatus(StatusPedido.REALIZADO);
		pedido.setCliente(clientesRepository
							.findById(dto.getCliente())
							.orElseThrow(() -> new RegraNegocioException("Codigo de cliente inválido")));

		List<ItemPedido> itemsPedido = converterItems(pedido, dto.getItems());
		pedidosRepository.save(pedido);
		itemsPedidoRepository.saveAll(itemsPedido);
		pedido.setItens(itemsPedido);
		return pedido;
	}

	private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> items){
        if(items.isEmpty()){
            throw new RegraNegocioException("Não é possível realizar um pedido sem items.");
        }

        return items
                .stream()
                .map( dto -> {
                    Integer idProduto = dto.getProduto();
                    Produto produto = produtosRepository
                            .findById(idProduto)
                            .orElseThrow(
                                    () -> new RegraNegocioException(
                                            "Código de produto inválido: "+ idProduto
                                    ));

                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);
                    return itemPedido;
                }).collect(Collectors.toList());
	}

	@Override
	public Optional<Pedido> obterPedidoService(Integer id) {
		return pedidosRepository.findByIdFetchItens(id);
	}

	@Override
	public Optional<Pedido> obterPedidoCompleto(Integer id) {
		return pedidosRepository.findByIdFetchItens(id);
	}
}
