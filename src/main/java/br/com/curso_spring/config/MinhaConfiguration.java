package br.com.curso_spring.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.curso_spring.Development;


@Development
public class MinhaConfiguration {

	//O que é um Bean? 
	
	/*@Bean(name="applicationName")
	public String applicationName() {
		return "Sistema de vendas";
	}*/
	@Bean
	public CommandLineRunner executar() {
		return args -> {
			System.out.println("RODANDO A CONFIGURAÇÃO DE DESENVOLVIMENTO");
		};
	}
}