package br.com.curso_spring.rest.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.curso_spring.domain.entity.Cliente;
import br.com.curso_spring.domain.entity.Pedido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {

	private Integer cliente;
	private BigDecimal total;
	private List<ItemPedidoDTO> items;
}
