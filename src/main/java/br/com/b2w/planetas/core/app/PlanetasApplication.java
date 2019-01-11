package br.com.b2w.planetas.core.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan({"br.com.b2w.planetas.*"})
@EnableAsync 
public class PlanetasApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlanetasApplication.class, args); 
	}

}

