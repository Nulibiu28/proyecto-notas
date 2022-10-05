package com.qds.prueba.notas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class NotasApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotasApplication.class, args);
	}

}
