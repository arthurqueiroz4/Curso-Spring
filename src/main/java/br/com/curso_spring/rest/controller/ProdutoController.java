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

import br.com.curso_spring.domain.entity.Produto;
import br.com.curso_spring.domain.repository.Produtos;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

	@Autowired
	private Produtos produtos;
	
	//-CRIAR, -ATUALIZAR, DELETAR, -PROCURAR
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Produto save(@RequestBody Produto produto) {
		return produtos.save(produto);
	}
	
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable("id") Integer id, 
						@RequestBody Produto produto) {
		
		produtos.
				findById(id)
				.map(produtoEncontrado -> {
					produto.setId(produtoEncontrado.getId());
					produtos.save(produto);
					return produtoEncontrado;
				})
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
		
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Produto> find (Produto produto){
		ExampleMatcher matcher = ExampleMatcher.matching()
								.withIgnoreCase()
								.withStringMatcher(StringMatcher.CONTAINING);
		
		Example<Produto> example = Example.of(produto, matcher);
		
		return produtos.findAll(example);
		
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		produtos.delete(produtos
						.findById(id)
						.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado")));
	}
}
