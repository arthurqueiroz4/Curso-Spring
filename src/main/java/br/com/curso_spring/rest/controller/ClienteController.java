package br.com.curso_spring.rest.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.curso_spring.domain.entity.Cliente;
import br.com.curso_spring.domain.repository.Clientes;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

	@Autowired
	private Clientes clientes;
	
	//OBTER DADOS DO FRONT -> GET
	@GetMapping("{id}")
	public ResponseEntity<Cliente> getClienteById(@PathVariable("id") Integer id) {
		Optional<Cliente> cliente = clientes.findById(id);
		if (cliente.isPresent()) {
			
			return ResponseEntity.ok(cliente.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	//SALVAR RECURSO NO SERVIDOR / QUANDO O RECURSO NAO EXISTE NO SERVIDOR
	@PostMapping("salvar")
	public ResponseEntity<Cliente> save(@RequestBody Cliente cliente) {
		Cliente clientesalvo = clientes.save(cliente);
		return ResponseEntity.ok(clientesalvo);
		
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Cliente> delete(@PathVariable("id") Integer id) {
		Optional<Cliente> cliente = clientes.findById(id);
		if (cliente.isPresent()) {
			clientes.delete(cliente.get());
			return ResponseEntity.noContent().build(); //codigo de status de sucesso porem nao é necessário retonar algo
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Object> update(@PathVariable("id") Integer id,
											@RequestBody Cliente cliente){
		return clientes
				.findById(id) //esse metodo retorna um optional
				.map(clienteExistente -> {	//se tiver valor presente, retorna um optional 
											//com o resultado da funcao passada
					cliente.setId(clienteExistente.getId());//troca o id do cliente que foi passado e
															//preenche com o id que ja existia daquele
															//usuario
					clientes.save(cliente);
					return ResponseEntity.noContent().build();
				}).orElseGet(()-> ResponseEntity.notFound().build());//se o valor estiver presente, retorna o valor, senao
																	//retorna o parametro passado
	}
	
	@GetMapping
	public ResponseEntity<Object> find (Cliente filtro){
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase()					 //permite algumas configurações
												.withStringMatcher(StringMatcher.CONTAINING);//como o ignoreCase e a forma de
																							//pesquisar a string.
		Example<Cliente> example = Example.of(filtro, matcher);// pega as propriedades que estao populadas 
																//no parametro e cria um example
		List<Cliente> lista = clientes.findAll(example); 
		return ResponseEntity.ok(lista);
																
	}
	
}
