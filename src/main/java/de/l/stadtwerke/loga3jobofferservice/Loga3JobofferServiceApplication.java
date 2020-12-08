package de.l.stadtwerke.loga3jobofferservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("de.l.stadtwerke.loga3jobofferservice.model")
public class Loga3JobofferServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(Loga3JobofferServiceApplication.class, args);
	}

}
