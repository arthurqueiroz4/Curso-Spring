package br.com.curso_spring.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.curso_spring.domain.entity.Produto;

public interface Produtos extends JpaRepository<Produto, Integer>{

}
