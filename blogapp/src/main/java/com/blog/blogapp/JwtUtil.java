package com.blog.blogapp;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	private final String SECRET = "mySuperSecretKey12345678901234567890";
	
	 private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());
	
	public String generateToken(String username) {
		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis()+3600000))
				.signWith(key,SignatureAlgorithm.HS256)
				.compact();
		
	}
	

	public String extractUsername(String token) {
		return extractClaims(token).getSubject();
	}
	
	//Validating Token
	
	public Boolean validatingToken(String username, String token) {
		String extractedUsername = extractUsername(token);
		return (username.equals(extractedUsername)&&!isTokenExpired(token));
	}
	
	public Boolean isTokenExpired(String token) {
		return extractClaims(token).getExpiration().before(new Date());
	}
	 private Claims extractClaims(String token) {
	        return Jwts.parserBuilder()
	                .setSigningKey(key) 
	                .build()
	                .parseClaimsJws(token) 
	                .getBody(); 
	    }
}
