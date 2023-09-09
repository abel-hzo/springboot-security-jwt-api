package com.abelhzo.jwt.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.abelhzo.jwt.repositories.UserRepository;

/**
 * @author: Abel HZO
 * @project: springboot-security-jwt-api
 * @file: UserDetailsServiceImpl.java
 * @location: México, Ecatepec, Edo. de México.
 * @date: Jueves 07 Septiembre 2023, 13:25:04
 * @description: El presente archivo UserDetailsServiceImpl.java fue creado por Abel HZO.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<com.abelhzo.jwt.entities.User> optionaluser = userRepository.findByUsername(username);
		
		if(optionaluser.isEmpty()) {
			throw new UsernameNotFoundException("Usuario no encontrado");
		}
		
		com.abelhzo.jwt.entities.User getuser = optionaluser.get();
		
		UserDetails user = User.withUsername(getuser.getUsername())
			.password(getuser.getPassword())
			.roles("ADMIN")
			.build();
		
		return user;
	}

}
