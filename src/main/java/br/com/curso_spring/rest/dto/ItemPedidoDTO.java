package br.com.curso_spring.rest.dto;

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
public class ItemPedidoDTO {

	private Integer produto;
	private Integer quantidade;
	
	
}
