package br.com.curso_spring.rest.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AtualizacaoStatusPedidoDTO {
    private String novoStatus;
}