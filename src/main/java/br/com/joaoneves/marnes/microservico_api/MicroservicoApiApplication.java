package br.com.joaoneves.marnes.microservico_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan; // Novo Import

@SpringBootApplication
@EntityScan(basePackages = "br.com.joaoneves.marnes.microservico_api.model") // Nova Anotação
public class MicroservicoApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicoApiApplication.class, args);
	}

}