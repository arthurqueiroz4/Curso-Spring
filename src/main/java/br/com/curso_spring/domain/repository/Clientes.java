package br.com.curso_spring.domain.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import br.com.curso_spring.domain.entity.Cliente;

public interface Clientes extends JpaRepository<Cliente, Integer>{

	//select c from Cliente c where c.nome like :nome
	//List<Cliente> findByNomeLike(String string);
	
	@Query(value = " select c from Cliente c where c.nome like :nome ")
	List<Cliente> encontrarPorNome(@Param("nome") String nome);
	
	// busca clientes que tem o nome ou o id passados e ordena pelo id
	List<Cliente> findByNomeOrIdOrderById(String nome, Integer id);
	
	@Query(value = "delete from Cliente c where c.nome =:nome")
	@Modifying //fazer atualiazação na tabela
	void deleteByNome(String nome);
	
	
	Cliente findOneByNome(String nome);
	
	boolean existsByNome(String nome);
	
	@Query("select c from Cliente c left join fetch c.pedidos where c.id = :id")
	Cliente findClienteFetchPedidos(Integer id);
}
