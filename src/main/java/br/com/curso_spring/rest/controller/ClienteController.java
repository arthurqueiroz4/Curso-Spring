package br.com.curso_spring.rest.controller;

import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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

import br.com.curso_spring.domain.entity.Cliente;
import br.com.curso_spring.domain.repository.Clientes;

@Controller
public class ClienteController {

	@Autowired
	private Clientes clientes;
	
	//OBTER DADOS DO FRONT -> GET
	@GetMapping("/api/clientes/{id}")
	@ResponseBody
	public ResponseEntity<Cliente> getClienteById(@PathVariable("id") Integer id) {
		Optional<Cliente> cliente = clientes.findById(id);
		if (cliente.isPresent()) {
			
			return ResponseEntity.ok(cliente.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	//SALVAR RECURSO NO SERVIDOR / QUANDO O RECURSO NAO EXISTE NO SERVIDOR
	@PostMapping("/api/clientes/salvar")
	@ResponseBody
	public ResponseEntity<Cliente> save(@RequestBody Cliente cliente) {
		Cliente clientesalvo = clientes.save(cliente);
		return ResponseEntity.ok(clientesalvo);
		
	}
	
	@DeleteMapping("/api/clientes/{id}")
	@ResponseBody
	public ResponseEntity<Cliente> delete(@PathVariable("id") Integer id) {
		Optional<Cliente> cliente = clientes.findById(id);
		if (cliente.isPresent()) {
			clientes.delete(cliente.get());
			return ResponseEntity.noContent().build(); //codigo de status de sucesso porem nao é necessário retonar algo
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/api/clientes/{id}")
	@ResponseBody
	public ResponseEntity<Object> update(@PathVariable("id") Integer id,
											@RequestBody Cliente cliente){
		return clientes
				.findById(id)
				.map(clienteExistente -> {
					cliente.setId(clienteExistente.getId());
					clientes.save(cliente);
					return ResponseEntity.noContent().build();
				}).orElseGet(()-> ResponseEntity.notFound().build());
		
	}
}
