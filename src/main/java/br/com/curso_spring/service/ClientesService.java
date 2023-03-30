package br.com.curso_spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.curso_spring.model.Cliente;
import br.com.curso_spring.repository.ClientesRepository;

@Service
public class ClientesService {
	@Autowired
	private ClientesRepository repository;
	
	/*Maneiras de injetar depedencia
	@Autowired
	public ClientesService(ClientesRepository repository) {
		this.repository = repository;
	}
	@Autowired
	public void setRepository(ClientesRepository repository) {
		this.repository = repository;
	}
	 */
	
	
	public void salvarCliente(Cliente cliente) {
		validarCliente(cliente);
		this.repository.persistir(cliente);

	}
	
	public void validarCliente(Cliente cliente) {
		//aplica valicoes
	}

}
