package br.com.curso_spring.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.curso_spring.domain.entity.Cliente;
import br.com.curso_spring.domain.entity.Pedido;

public interface Pedidos extends JpaRepository<Pedido, Integer>{

	List<Pedido> findByCliente(Cliente cliente);
	
}
