package com.abelhzo.jwt.mappers;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.Named;

import com.abelhzo.jwt.dtos.UserDTO;
import com.abelhzo.jwt.dtos.UserRolDTO;
import com.abelhzo.jwt.entities.User;
import com.abelhzo.jwt.entities.UserRol;

/**
 * @author: Abel HZO
 * @project: springboot-security-jwt-api
 * @file: UserRolMapper.java
 * @location: México, Ecatepec, Edo. de México.
 * @date: Martes 05 Septiembre 2023, 14:05:55
 * @description: El presente archivo UserRolMapper.java fue creado por Abel HZO.
 */
@Mapper(componentModel = ComponentModel.SPRING)
public interface UserRolMapper {
	
	@Named(value = "customUserRol")
	@Mapping(target = "rol", source = "rolDTO")
	@Mapping(target = "user", source = "userDTO", qualifiedByName = "customUser")
	UserRol toUserRol(UserRolDTO userRolDTO);
	
	@Named(value = "customUserRolDTO")
	@Mapping(target = "rolDTO", source = "rol")
	UserRolDTO toUserRol(UserRol userRol);
	
	@Named(value = "customUser")
//	@Mapping(target = "idUser", ignore = true)
//	@Mapping(target = "username", ignore = true)
//	@Mapping(target = "password", ignore = true)
//	@Mapping(target = "email", ignore = true)
//	@Mapping(target = "birthday", ignore = true)
//	@Mapping(target = "photo", ignore = true)
//	@Mapping(target = "created", ignore = true)
	@Mapping(target = "userRols", ignore = true)
	User toUser(UserDTO userDTO);
	
	@Named("stringToTimestamp")
	default Timestamp map(String value) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedTimestamp = value.formatted(formatter);
		return Timestamp.valueOf(formattedTimestamp);
	}		

}
