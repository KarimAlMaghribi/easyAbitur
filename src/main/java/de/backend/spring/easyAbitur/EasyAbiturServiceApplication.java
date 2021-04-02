package de.backend.spring.easyAbitur;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("de.l.development.easyAbitur.model")
public class EasyAbiturServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EasyAbiturServiceApplication.class, args);
	}

}
