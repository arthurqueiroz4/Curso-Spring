package br.com.curso_spring;

import java.math.BigDecimal;
import java.time.LocalDate;
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
import br.com.curso_spring.domain.repository.Clientes;

@SpringBootApplication
@RestController
public class Application {
	
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
