package br.com.ijSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories(basePackages = "br.com.ijSystem.Repositories")
@EntityScan(basePackages = "br.com.ijSystem.Entities")
@SpringBootApplication
public class MeuProjetoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeuProjetoApplication.class, args);
	}

}
