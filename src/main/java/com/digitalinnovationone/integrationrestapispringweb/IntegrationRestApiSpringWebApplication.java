package com.digitalinnovationone.integrationrestapispringweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients
public class IntegrationRestApiSpringWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntegrationRestApiSpringWebApplication.class, args);
	}

}
