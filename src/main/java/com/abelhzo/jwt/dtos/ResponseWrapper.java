package com.abelhzo.jwt.dtos;

/**
 * @author: Abel HZO
 * @project: springboot-security-jwt-api
 * @file: ResponseWrapper.java
 * @location: México, Ecatepec, Edo. de México.
 * @date: Martes 05 Septiembre 2023, 14:03:53
 * @description: El presente archivo ResponseWrapper.java fue creado por Abel HZO.
 */
public class ResponseWrapper {
	
	private String time;
	private String currentUser;
//	private Collection<? extends GrantedAuthority> authorities;
	private TypeMessage typeMessage;
	private String message;
	private Object body;

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(String currentUser) {
		this.currentUser = currentUser;
	}

	public TypeMessage getTypeMessage() {
		return typeMessage;
	}

	public void setTypeMessage(TypeMessage typeMessage) {
		this.typeMessage = typeMessage;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getBody() {
		return body;
	}

	public void setBody(Object body) {
		this.body = body;
	}	

}
