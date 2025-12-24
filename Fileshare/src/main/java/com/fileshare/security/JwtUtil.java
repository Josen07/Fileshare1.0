package com.fileshare.security;

import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtUtil {

	// MUST be at least 32 characters for HS256
	private static final String SECRET = "FILES_SHARE_SECRET_KEY_1234567890";

	private static final Key SECRET_KEY = Keys.hmacShaKeyFor(SECRET.getBytes());

	private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour

	public static String generateToken(String username) {

		return Jwts.builder().setSubject(username).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)).signWith(SECRET_KEY).compact();
	}

	public static String validateToken(String token) {

		return Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody().getSubject();
	}
}
