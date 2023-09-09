package com.abelhzo.jwt.dtos;

/**
 * @author: Abel HZO
 * @project: springboot-security-jwt-api
 * @file: RolDTO.java
 * @location: México, Ecatepec, Edo. de México.
 * @date: Martes 05 Septiembre 2023, 13:59:03
 * @description: El presente archivo RolDTO.java fue creado por Abel HZO.
 */
public class RolDTO {
	
	private Integer idRol;
	private String rol;
	
	public RolDTO() {
		super();
	}

	public RolDTO(Integer idRol) {
		super();
		this.idRol = idRol;
	}

	public Integer getIdRol() {
		return idRol;
	}

	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}	

}
