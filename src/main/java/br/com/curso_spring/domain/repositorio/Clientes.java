package br.com.curso_spring.domain.repositorio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
}
