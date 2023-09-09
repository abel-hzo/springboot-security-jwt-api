package com.abelhzo.jwt.dtos;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author: Abel HZO
 * @project: springboot-security-jwt-api
 * @file: UserDTO.java
 * @location: México, Ecatepec, Edo. de México.
 * @date: Martes 05 Septiembre 2023, 13:59:16
 * @description: El presente archivo UserDTO.java fue creado por Abel HZO.
 */
public class UserDTO {
	
	private Long idUser;
	private String username;
	private String password;
	private String email;
	private LocalDate birthday;
	private byte[] photo;
	private Timestamp created;
	private List<UserRolDTO> userRolsDTO = new ArrayList<UserRolDTO>(0);
	@JsonIgnore
	private MultipartFile file;
	
	public UserDTO() {
		super();
	}

	public UserDTO(Long idUser) {
		super();
		this.idUser = idUser;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public Timestamp getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public List<UserRolDTO> getUserRolsDTO() {
		return userRolsDTO;
	}

	public void setUserRolsDTO(List<UserRolDTO> userRolsDTO) {
		this.userRolsDTO = userRolsDTO;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}	

}
