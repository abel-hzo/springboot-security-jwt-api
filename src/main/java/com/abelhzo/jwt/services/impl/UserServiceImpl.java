package com.abelhzo.jwt.services.impl;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.abelhzo.jwt.dtos.ResponseWrapper;
import com.abelhzo.jwt.dtos.RolDTO;
import com.abelhzo.jwt.dtos.TypeMessage;
import com.abelhzo.jwt.dtos.UserDTO;
import com.abelhzo.jwt.dtos.UserRolDTO;
import com.abelhzo.jwt.entities.User;
import com.abelhzo.jwt.mappers.UserMapper;
import com.abelhzo.jwt.repositories.UserRepository;
import com.abelhzo.jwt.services.UserService;
import com.abelhzo.jwt.utils.Utils;

/**
 * @author: Abel HZO
 * @project: springboot-security-jwt-api
 * @file: UserServiceImpl.java
 * @location: México, Ecatepec, Edo. de México.
 * @date: Martes 05 Septiembre 2023, 14:14:25
 * @description: El presente archivo UserServiceImpl.java fue creado por Abel HZO.
 */
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private Utils utils;

	@Override
	public ResponseEntity<ResponseWrapper> register(UserDTO userDTO) {
		
		userDTO.setIdUser(System.currentTimeMillis());
		userDTO.setCreated(new Timestamp(System.currentTimeMillis()));
		userDTO.setPhoto(utils.loadDefaultImageUser());
		userDTO.setPassword("{noop}" + userDTO.getPassword());
			UserRolDTO userRolDTO = new UserRolDTO();
			userRolDTO.setIdUserRol(System.currentTimeMillis());
			userRolDTO.setUserDTO(new UserDTO(userDTO.getIdUser()));
			userRolDTO.setRolDTO(new RolDTO(1));
			userRolDTO.setCreated(new Timestamp(System.currentTimeMillis()));
		userDTO.getUserRolsDTO().add(userRolDTO);	
		
		User user = userMapper.toUser(userDTO);
		
		User save = userRepository.save(user);
		
		ResponseEntity<ResponseWrapper> responseEntity = utils.buildResponseEntity(TypeMessage.SUCCESS, userMapper.toUserDTO(save));
		
		return responseEntity;
	}

	@Override
	public ResponseEntity<ResponseWrapper> getPhoto(Long idUser) {
		
		byte[] photo = userRepository.getPhoto(idUser);
		
		ResponseEntity<ResponseWrapper> responseEntity;
		
		if(photo == null) {
			responseEntity = utils.buildResponseEntity(TypeMessage.FAILURE, "No hay foto.");
		} else {
			responseEntity = utils.buildResponseEntity(TypeMessage.SUCCESS, photo);
		}
		
		return responseEntity;
	}

	@Override
	public ResponseEntity<ResponseWrapper> getAllUsers(Integer noToShow, Integer page) {
		
		ResponseEntity<ResponseWrapper> responseEntity;
		
		if(userRepository.getAllUsers(noToShow, page).isEmpty()) {
			responseEntity = utils.buildResponseEntity(TypeMessage.FAILURE, "Sin datos.");
		} else {
			responseEntity = utils.buildResponseEntity(TypeMessage.SUCCESS, userRepository.getAllUsers(noToShow, page));
		}
		
		return responseEntity;
	}

	@Override
	public ResponseEntity<ResponseWrapper> getUser(Long idUser) {
		Optional<User> findById = userRepository.findById(idUser);
		
		ResponseEntity<ResponseWrapper> responseEntity;
		
		if(findById.isEmpty()) {
			responseEntity = utils.buildResponseEntity(TypeMessage.FAILURE, "El usuario no fue encontrado.");
		} else {
			responseEntity = utils.buildResponseEntity(TypeMessage.SUCCESS, userMapper.toUserDTO(findById.get()));
		}
		
		return responseEntity;
	}
	
	@Override
	public ResponseEntity<ResponseWrapper> getUser(String username) {
		
		Optional<User> findByUsername = userRepository.findByUsername(username);
		
		if(findByUsername.isEmpty())
			return utils.buildResponseEntity(TypeMessage.SUCCESS, "Usuario Inexistente");
		
		UserDTO userDTO = userMapper.toUserDTO(findByUsername.get());
		
		return utils.buildResponseEntity(TypeMessage.SUCCESS, userDTO);
	}	

	@Override
	public ResponseEntity<ResponseWrapper> updateUser(UserDTO userDTO) {
		
		userRepository.updateUser(userDTO.getUsername(), 
							      userDTO.getEmail(), 
							      userDTO.getPassword(), 
							      userDTO.getBirthday(), 
							      userDTO.getIdUser());
		
		if(!userDTO.getUserRolsDTO().isEmpty())
			userRepository.updateUserRol(userDTO.getUserRolsDTO().get(0).getRolDTO().getIdRol(), userDTO.getIdUser());
		
		if(userDTO.getFile() != null) {
			updatePhoto(userDTO);
		}
		
		return utils.buildResponseEntity(TypeMessage.SUCCESS, "El usuario fue modificado.");
	}
	
	@Override
	public ResponseEntity<ResponseWrapper> saveUser(UserDTO userDTO) {
		
		userDTO.setIdUser(System.currentTimeMillis());
		userDTO.setCreated(new Timestamp(System.currentTimeMillis()));
		userDTO.setPassword("{noop}" + userDTO.getPassword());
		
		userDTO.getUserRolsDTO().get(0).setIdUserRol(System.currentTimeMillis());
		userDTO.getUserRolsDTO().get(0).setUserDTO(new UserDTO(userDTO.getIdUser()));
		userDTO.getUserRolsDTO().get(0).setCreated(new Timestamp(System.currentTimeMillis()));
		
		if(userDTO.getFile() == null)
			userDTO.setPhoto(utils.loadDefaultImageUser());
		else
			try {
				userDTO.setPhoto(userDTO.getFile().getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		User user = userMapper.toUser(userDTO);
		
		User save = userRepository.save(user);
		
		return utils.buildResponseEntity(TypeMessage.SUCCESS, userMapper.toUserDTO(save));
	}	

	@Override
	public ResponseEntity<ResponseWrapper> updatePhoto(UserDTO userDTO) {
		
		try {
			userRepository.updatePhoto(userDTO.getFile().getBytes(), userDTO.getIdUser());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return utils.buildResponseEntity(TypeMessage.SUCCESS, "La foto fue modificada.");
	}

	@Override
	public ResponseEntity<ResponseWrapper> getNoPages(Integer noToShow) {
		int noPages = userRepository.getNoPages(noToShow);
		return utils.buildResponseEntity(TypeMessage.SUCCESS, noPages);
	}

	@Override
	public ResponseEntity<ResponseWrapper> getCurrentUser() {
		
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		
		return utils.buildResponseEntity(TypeMessage.SUCCESS, null);
	}	

}
