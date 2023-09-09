package com.abelhzo.jwt.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants.ComponentModel;

import com.abelhzo.jwt.dtos.UserDTO;
import com.abelhzo.jwt.entities.User;

/**
 * @author: Abel HZO
 * @project: springboot-security-jwt-api
 * @file: UserMapper.java
 * @location: México, Ecatepec, Edo. de México.
 * @date: Martes 05 Septiembre 2023, 14:05:09
 * @description: El presente archivo UserMapper.java fue creado por Abel HZO.
 */
@Mapper(componentModel = ComponentModel.SPRING, uses = { UserRolMapper.class })
public interface UserMapper {
	
	@Mapping(target = "userRols", source = "userRolsDTO", qualifiedByName = "customUserRol")
	User toUser(UserDTO userDTO);

	@Mapping(target = "userRolsDTO", source = "userRols", qualifiedByName = "customUserRolDTO")
	UserDTO toUserDTO(User user);	

}
