package com.mavis.smart.docker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SmartDockerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartDockerApplication.class, args);
	}

	@RequestMapping("/")
	public String hello() {
		return "hello docker";
	}
}
