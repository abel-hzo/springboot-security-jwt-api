package com.abelhzo.jwt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abelhzo.jwt.dtos.ResponseWrapper;
import com.abelhzo.jwt.dtos.UserDTO;
import com.abelhzo.jwt.services.UserService;

/**
 * @author: Abel HZO
 * @project: springboot-security-jwt-api
 * @file: UserController.java
 * @location: México, Ecatepec, Edo. de México.
 * @date: Martes 05 Septiembre 2023, 14:17:15
 * @description: El presente archivo UserController.java fue creado por Abel HZO.
 */
@RestController
@RequestMapping("/users")
public class UserController implements UserService {
	
	@Autowired
	private UserService userService;

	@Override
	@PostMapping("/register")
	public ResponseEntity<ResponseWrapper> register(UserDTO userDTO) {
		return userService.register(userDTO);
	}

	@Override
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/photo/{idUser}")
	public ResponseEntity<ResponseWrapper> getPhoto(@PathVariable("idUser") Long idUser) {
		return userService.getPhoto(idUser);
	}

	@Override
	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	@GetMapping("/list/{noToShow}/{page}")
	public ResponseEntity<ResponseWrapper> getAllUsers(@PathVariable("noToShow") Integer noToshow , 
													   @PathVariable("page") Integer page) {
		return userService.getAllUsers(noToshow, page);
	}

	@Override
//	@Secured({ "ROLE_ADMIN" })
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/get/{idUser}")
	public ResponseEntity<ResponseWrapper> getUser(@PathVariable("idUser") Long idUser) {
		return userService.getUser(idUser);
	}
	
	@Override
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/get/user")
	public ResponseEntity<ResponseWrapper> getUser(String username) {
		return userService.getUser(username);
	}	

	@Override
	@Secured({ "ROLE_ADMIN" })
	@PostMapping("/update")
	public ResponseEntity<ResponseWrapper> updateUser(UserDTO userDTO) {
		return userService.updateUser(userDTO);
	}
	
	@Override
	@Secured({ "ROLE_ADMIN" })
	@PostMapping("/save")
	public ResponseEntity<ResponseWrapper> saveUser(UserDTO userDTO) {
		return userService.saveUser(userDTO);
	}

	@Override
	@PreAuthorize("isAuthenticated()")
	@PutMapping("/uphoto")
	public ResponseEntity<ResponseWrapper> updatePhoto(UserDTO userDTO) {
		return userService.updatePhoto(userDTO);
	}

	@Override
	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	@GetMapping("/noPages/{noToShow}")
	public ResponseEntity<ResponseWrapper> getNoPages(@PathVariable("noToShow") Integer noToShow) {
		return userService.getNoPages(noToShow);
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/currentUser")
	public ResponseEntity<ResponseWrapper> getCurrentUser() {
		return userService.getCurrentUser();
	}	

}
