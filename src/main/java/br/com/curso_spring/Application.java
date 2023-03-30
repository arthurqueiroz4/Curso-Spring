package br.com.curso_spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * Spring Boot application starter class
 */
@SpringBootApplication
@RestController
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    @Cachorro
    private Animal animal;
    
    @Bean
    @Qualifier("executarBarulho")
    public CommandLineRunner executarBarulho() {
    	return args -> {
    		this.animal.fazerBarulho();
    	};
    }
    
    @Value("${application.name}")
	private String applicationName;
	
    @GetMapping("/hello")
    public String helloWord() {
    	return applicationName;
    }
}
