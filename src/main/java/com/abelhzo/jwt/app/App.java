package com.abelhzo.jwt.app;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import jakarta.annotation.PostConstruct;

/**
 * @author: Abel HZO
 * @project: springboot-security-jwt-api
 * @file: App.java
 * @location: México, Ecatepec, Edo. de México.
 * @date: Martes 05 Septiembre 2023, 12:33:23
 * @description: El presente archivo App.java fue creado por Abel HZO.
 */
@SpringBootApplication
@ComponentScan(basePackages = { "com.abelhzo.jwt" })
public class App {
	
	@PostConstruct
	public void init() {
		//Entra la fecha correcta al Rest y la graba en BD correctamente.
		TimeZone.setDefault(TimeZone.getTimeZone("America/Mexico_City"));
	}	

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}
