package com.abelhzo.jwt.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author: Abel HZO
 * @project: springboot-security-jwt-api
 * @file: JwtUtilService.java
 * @location: México, Ecatepec, Edo. de México.
 * @date: Jueves 07 Septiembre 2023, 13:38:20
 * @description: El presente archivo JwtUtilService.java fue creado por Abel HZO.
 */
@Service
public class JwtUtilService {
	
	// LLAVE_MUY_SECRETA [Base64] =>
	private static final String JWT_SECRET_KEY = "TExBVkVfTVVZX1NFQ1JFVEE=";

//	private static final long JWT_TOKEN_VALIDITY = 1000 * 60 * 60 * (long) 1;       //1 hora   
//	private static final long JWT_TOKEN_VALIDITY = 1000 * 60 * 60 * (long) 1 / 3;   //20min
	private static final long JWT_TOKEN_VALIDITY = 1000 * 60 * 60 * (long) 1 / 60;  //5min
	
	//Entre 12 para que sean 5 minutos.
	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		return claimsResolver.apply(extractAllClaims(token));
	}

	private Claims extractAllClaims(String token) {
		
		Claims claims = null;
		
		claims = Jwts
				.parser()
				.setSigningKey(JWT_SECRET_KEY)
				.parseClaimsJws(token)
				.getBody();
		
		return claims;
	}

	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		// Agregando informacion adicional como "claim"
		var rol = userDetails
				.getAuthorities()
				.stream()
				.collect(Collectors.toList())
				.get(0);
		claims.put("rol", rol);
		
		return createToken(claims, userDetails.getUsername());
	}

	private String createToken(Map<String, Object> claims, String subject) {
		return Jwts
				.builder()
				.setClaims(claims)
				.setSubject(subject)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
				.signWith(SignatureAlgorithm.HS256, JWT_SECRET_KEY).compact();
	}

	public boolean validateToken(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}	

}
