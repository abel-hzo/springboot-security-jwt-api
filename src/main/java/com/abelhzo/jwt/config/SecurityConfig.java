package com.abelhzo.jwt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.abelhzo.jwt.security.JwtRequestFilter;

/**
 * @author: Abel HZO
 * @project: springboot-security-jwt-api
 * @file: SecurityConfig.java
 * @location: México, Ecatepec, Edo. de México.
 * @date: Jueves 07 Septiembre 2023, 12:40:51
 * @description: El presente archivo SecurityConfig.java fue creado por Abel HZO.
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true,
					securedEnabled = true,
					jsr250Enabled = true)
public class SecurityConfig {
	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((authz) -> authz
					.requestMatchers("/public/**").permitAll()
					.requestMatchers("/users/register").permitAll()
					.anyRequest().authenticated())
			.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
			.sessionManagement((session) -> session
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.cors()
			.and()
			.csrf().disable();
		return http.build();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.addAllowedOrigin("http://localhost:8079/");
		configuration.addAllowedMethod("*");
	    configuration.addAllowedHeader("*");
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
	
}
