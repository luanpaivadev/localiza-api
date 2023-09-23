package com.luanpaiva.localizaapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class LocalizaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(LocalizaApiApplication.class, args);
	}

}
