package com.abelhzo.jwt.dtos;

import org.springframework.http.HttpStatus;

/**
 * @author: Abel HZO
 * @project: springboot-security-jwt-api
 * @file: TypeMessage.java
 * @location: México, Ecatepec, Edo. de México.
 * @date: Martes 05 Septiembre 2023, 13:59:43
 * @description: El presente archivo TypeMessage.java fue creado por Abel HZO.
 */
public enum TypeMessage {
	
	SUCCESS("Operación realizada con exito.", HttpStatus.OK),

	BAD_REQUEST("Datos invalidos", HttpStatus.BAD_REQUEST),

	NOT_FOUND("No hay datos para esta consulta.", HttpStatus.NO_CONTENT),

	DUPLICATED("No pueden guardarse datos existentes.", HttpStatus.CONFLICT),

	FAILURE("No se puedo realizar la operación.", HttpStatus.EXPECTATION_FAILED),
	
	FORBIDDEN("Token Invalido", HttpStatus.FORBIDDEN),

	INVALID_LOGIN("Nombre de usuario o contraseña invalidos.", HttpStatus.UNAUTHORIZED);

	private final String message;
	private final HttpStatus status;

	TypeMessage(String message, HttpStatus status) {
		this.message = message;
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public HttpStatus getStatus() {
		return status;
	}	

}
