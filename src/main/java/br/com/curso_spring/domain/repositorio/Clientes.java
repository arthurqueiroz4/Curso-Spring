package br.com.curso_spring.domain.repositorio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.curso_spring.domain.entity.Cliente;

public interface Clientes extends JpaRepository<Cliente, Integer>{

	List<Cliente> findByNomeLike(String string);

	
}
