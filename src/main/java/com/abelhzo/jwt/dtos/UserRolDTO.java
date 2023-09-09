package com.abelhzo.jwt.dtos;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author: Abel HZO
 * @project: springboot-security-jwt-api
 * @file: UserRolDTO.java
 * @location: México, Ecatepec, Edo. de México.
 * @date: Martes 05 Septiembre 2023, 13:59:28
 * @description: El presente archivo UserRolDTO.java fue creado por Abel HZO.
 */
public class UserRolDTO {
	
	private Long idUserRol;
	@JsonIgnore
	private UserDTO userDTO;
	private RolDTO rolDTO;
	private Timestamp created;

	public Long getIdUserRol() {
		return idUserRol;
	}

	public void setIdUserRol(Long idUserRol) {
		this.idUserRol = idUserRol;
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	public RolDTO getRolDTO() {
		return rolDTO;
	}

	public void setRolDTO(RolDTO rolDTO) {
		this.rolDTO = rolDTO;
	}

	public Timestamp getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}	

}
