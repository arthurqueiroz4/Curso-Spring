package br.com.curso_spring.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import static org.springframework.http.HttpStatus.*;
import br.com.curso_spring.domain.entity.Produto;
import br.com.curso_spring.domain.repository.Produtos;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

	@Autowired
	private Produtos repository;
	
	//-CRIAR, -ATUALIZAR, DELETAR, -PROCURAR
	
	@PostMapping
	@ResponseStatus(CREATED)
	public Produto save(@RequestBody Produto produto) {
		return repository.save(produto);
	}
	
	@PutMapping("{id}")
	@ResponseStatus(NO_CONTENT)
	public void update(@PathVariable("id") Integer id, 
						@RequestBody Produto produto) {
		
		repository.
				findById(id)
				.map(produtoEncontrado -> {
					produto.setId(produtoEncontrado.getId());
					repository.save(produto);
					return produtoEncontrado;
				})
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
		
	}
	
	@GetMapping
	@ResponseStatus(OK)
	public List<Produto> find (Produto produto){
		ExampleMatcher matcher = ExampleMatcher.matching()
								.withIgnoreCase()
								.withStringMatcher(StringMatcher.CONTAINING);
		
		Example<Produto> example = Example.of(produto, matcher);
		
		return repository.findAll(example);
		
	}
	
	@GetMapping("{id}")
	public Produto getById (@PathVariable Integer id){
		return repository
					.findById(id)
					.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
					
		
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		repository.delete(repository
						.findById(id)
						.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado")));
	}
}
