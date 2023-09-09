package com.abelhzo.jwt.services;

import org.springframework.http.ResponseEntity;

import com.abelhzo.jwt.dtos.ResponseWrapper;
import com.abelhzo.jwt.dtos.UserDTO;

/**
 * @author: Abel HZO
 * @project: springboot-security-jwt-api
 * @file: UserService.java
 * @location: México, Ecatepec, Edo. de México.
 * @date: Martes 05 Septiembre 2023, 14:12:48
 * @description: El presente archivo UserService.java fue creado por Abel HZO.
 */
public interface UserService {
	
	ResponseEntity<ResponseWrapper> register(UserDTO userDTO);
	
	ResponseEntity<ResponseWrapper> getPhoto(Long idUser);
	
	ResponseEntity<ResponseWrapper> getAllUsers(Integer noToShow, Integer page);
	
	ResponseEntity<ResponseWrapper> getUser(Long idUser);
	
	ResponseEntity<ResponseWrapper> getUser(String username);
	
	ResponseEntity<ResponseWrapper> updateUser(UserDTO userDTO);
	
	ResponseEntity<ResponseWrapper> saveUser(UserDTO userDTO);
	
	ResponseEntity<ResponseWrapper> updatePhoto(UserDTO userDTO);
	
	ResponseEntity<ResponseWrapper> getNoPages(Integer noToShow);
	
	ResponseEntity<ResponseWrapper> getCurrentUser();	

}
