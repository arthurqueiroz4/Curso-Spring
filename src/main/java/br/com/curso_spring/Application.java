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
			clientes.salvar(new Cliente("Arthur"));
			clientes.salvar(new Cliente("Nayra"));
			
			System.out.println("Mostrando");
			List<Cliente> todos = clientes.obterTodos();
			todos.forEach(System.out::println);
			
			todos.forEach(c -> {
				c.setNome(c.getNome()+" atualizado");
				clientes.atualizar(c);
			});
			
			System.out.println("Atualizando");
			todos = clientes.obterTodos();
			todos.forEach(System.out::println);
			
			System.out.println("Procurando");
			clientes.buscarPorNome("Na").forEach(System.out::println);;
			System.out.println("Deletando");
			clientes.obterTodos().forEach(c ->{
				clientes.deletar(c);
			});
			//todos = clientes.obterTodos();
			//todos.forEach(System.out::println);
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
