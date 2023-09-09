package com.abelhzo.jwt.config;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author: Abel HZO
 * @project: springboot-security-jwt-api
 * @file: DataSourceConfig.java
 * @location: México, Ecatepec, Edo. de México.
 * @date: Martes 05 Septiembre 2023, 12:31:34
 * @description: El presente archivo DataSourceConfig.java fue creado por Abel HZO.
 */
@Configuration
@EntityScan(basePackages = { "com.abelhzo.jwt.entities" })
@EnableJpaRepositories(basePackages = { "com.abelhzo.jwt.repositories" })
public class DataSourceConfig {
	
	private final String PATH_DATABASE = new FileSystemResource("")
			.getFile()
			.getAbsolutePath()
			.concat("/src/main/resources/jwtdb;TIME ZONE=America/Mexico_City");

	@Bean
	public DataSource dataSourceH2() {
		DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.driverClassName("org.h2.Driver");
		dataSourceBuilder.url("jdbc:h2:" + PATH_DATABASE);
		dataSourceBuilder.username("abelhzo");
		dataSourceBuilder.password("54321");
		return dataSourceBuilder.build();
	}
	
}
