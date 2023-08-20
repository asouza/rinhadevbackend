package com.deveficiente.rinhadevbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class RinhadevbackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(RinhadevbackendApplication.class, args);
	}

}
