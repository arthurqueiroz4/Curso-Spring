package br.com.curso_spring.rest.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.curso_spring.domain.entity.Cliente;
import br.com.curso_spring.domain.repository.Clientes;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

	@Autowired
	private Clientes clientes;
	
	//OBTER DADOS DO FRONT -> GET
	@GetMapping("{id}")
	public Cliente getClienteById(@PathVariable("id") Integer id) {
		return clientes
					.findById(id)
					.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente nao encontrado"));
	}
	
	//SALVAR RECURSO NO SERVIDOR / QUANDO O RECURSO NAO EXISTE NO SERVIDOR
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)//Http correto quando cria algo no servidor
	public Cliente save(@RequestBody Cliente cliente) {
		return clientes.save(cliente);
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)//codigo de status de sucesso porem nao é necessário retonar algo
	public void delete(@PathVariable("id") Integer id) {
		clientes.findById(id)
			.map(cliente -> {
				clientes.delete(cliente);
				return cliente;
			})
			.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente nao encontrado"));
			
	}
	
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)//codigo de status de sucesso porem nao é necessário retonar algo
	public void update(@PathVariable("id") Integer id,
											@RequestBody Cliente cliente){
		clientes
				.findById(id) //esse metodo retorna um optional
				.map(clienteExistente -> {	//se tiver valor presente, retorna um optional 
											//com o resultado da funcao passada
					cliente.setId(clienteExistente.getId());//troca o id do cliente que foi passado e
															//preenche com o id que ja existia daquele
															//usuario
					clientes.save(cliente);
					return cliente;
				}).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente nao encontrado"));
																	//se o valor estiver presente, retorna o valor, senao
																	//retorna o parametro passado
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.ACCEPTED)
	public List<Cliente> find (Cliente filtro){
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase()					 //permite algumas configurações
												.withStringMatcher(StringMatcher.CONTAINING);//como o ignoreCase e a forma de
																							//pesquisar a string.
		Example<Cliente> example = Example.of(filtro, matcher);// pega as propriedades que estao populadas 
																//no parametro e cria um example
		return clientes.findAll(example); 
																
	}
	
}
