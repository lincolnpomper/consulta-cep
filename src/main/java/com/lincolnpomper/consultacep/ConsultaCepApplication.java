package com.lincolnpomper.consultacep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.lincolnpomper.consultacep")
public class ConsultaCepApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsultaCepApplication.class, args);
	}
}

