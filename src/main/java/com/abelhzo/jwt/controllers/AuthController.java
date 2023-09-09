package com.abelhzo.jwt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abelhzo.jwt.dtos.UserDTO;
import com.abelhzo.jwt.security.JwtUtilService;
import com.abelhzo.jwt.security.TokenDTO;

/**
 * @author: Abel HZO
 * @project: springboot-security-jwt-api
 * @file: AuthController.java
 * @location: México, Ecatepec, Edo. de México.
 * @date: Jueves 07 Septiembre 2023, 12:37:54
 * @description: El presente archivo AuthController.java fue creado por Abel HZO.
 */
@RestController
@RequestMapping("/public")
public class AuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtUtilService jwtUtilService; 
	
	@PostMapping("/token")
	public ResponseEntity<TokenDTO> token(UserDTO userDTO) {
		
		//Para que funcione el metodo authenticate, es necesario tener creado el UserDetailsService.
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword()));
		
		UserDetails userDetails = userDetailsService.loadUserByUsername(userDTO.getUsername());
		
		String token = jwtUtilService.generateToken(userDetails);
		TokenDTO tokenDTO = new TokenDTO();
		tokenDTO.setToken(token);
		
		return new ResponseEntity<TokenDTO>(tokenDTO, HttpStatus.OK);
		
	}

}