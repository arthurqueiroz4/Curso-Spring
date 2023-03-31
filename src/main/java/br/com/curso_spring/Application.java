package br.com.curso_spring;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.curso_spring.domain.entity.Cliente;
import br.com.curso_spring.domain.repositorio.Clientes;

/**
 *
 * Spring Boot application starter class
 */
@SpringBootApplication
@RestController
public class Application {
	
	@Bean
	public CommandLineRunner init(@Autowired Clientes clientes) {
		return args -> {
			System.out.println("Salvando");
			clientes.save(new Cliente("Arthur"));
			clientes.save(new Cliente("Nayra"));
			
			System.out.println("Mostrando pelo nome");
			List<Cliente> todos = clientes.encontrarPorNome("Arthur");
			todos.forEach(System.out::println);
			
			boolean existe = clientes.existsByNome("Arthur");
			System.out.println(existe);
		};
	}
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    
    @Value("${application.name}")
	private String applicationName;
	
    @GetMapping("/hello")
    public String helloWord() {
    	return applicationName;
    }
}
