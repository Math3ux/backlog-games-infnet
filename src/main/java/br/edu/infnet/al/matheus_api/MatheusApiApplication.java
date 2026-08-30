package br.edu.infnet.al.matheus_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MatheusApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MatheusApiApplication.class, args);
	}

}
