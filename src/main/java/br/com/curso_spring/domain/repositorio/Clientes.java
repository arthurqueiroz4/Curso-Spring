package br.com.curso_spring.domain.repositorio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import br.com.curso_spring.domain.entity.Cliente;

@Repository
public class Clientes {

	private static String INSERT = "insert into cliente (nome) values (?) ";
	private static String SELECT_ALL = "select * from cliente";
	private static String UPDATE = "update cliente set nome = ? where id = ?";
	private static String DELETE = "delete from cliente where id = ?";
	
	@Autowired
	private JdbcTemplate jdbc;
	
	public Cliente salvar(Cliente cliente) {
		jdbc.update(INSERT, new Object[] {cliente.getNome()});
		return cliente;
	}
	
	public Cliente atualizar(Cliente cliente) {
		jdbc.update(UPDATE, new Object[] {cliente.getNome(), cliente.getId()});
		return cliente;
	}
	
	public void deletar(Cliente cliente) {
		deletar(cliente.getId());
	}
	
	public void deletar(Integer id) {
		jdbc.update(DELETE, new Object[] {id});
	}
	
	public List<Cliente> buscarPorNome(String nome) {
		return jdbc.query(SELECT_ALL.concat(" where nome like ?"),
				new Object[] {
						"%"+nome+"%"
				},
				new RowMapper<Cliente>() {
			@Override
			public Cliente mapRow(ResultSet resultset, int i) throws SQLException{
				Integer id = resultset.getInt("id");
				String nome = resultset.getString("nome");
				return new Cliente(id,nome);
			}
		});
	}
	
	public List<Cliente> obterTodos(){
		return jdbc.query(SELECT_ALL, new RowMapper<Cliente>() {
			@Override
			public Cliente mapRow(ResultSet resultset, int i) throws SQLException{
				Integer id = resultset.getInt("id");
				String nome = resultset.getString("nome");
				return new Cliente(id,nome);
			}
		});
	}
}
