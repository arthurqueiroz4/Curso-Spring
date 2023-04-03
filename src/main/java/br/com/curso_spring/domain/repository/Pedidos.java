package br.com.curso_spring.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.curso_spring.domain.entity.Cliente;
import br.com.curso_spring.domain.entity.Pedido;

public interface Pedidos extends JpaRepository<Pedido, Integer>{

	List<Pedido> findByCliente(Cliente cliente);
	
	@Query(" select p from Pedido p left join fetch p.itens where p.id = :id ")
    Optional<Pedido> findByIdFetchItens(@Param("id") Integer id);
}