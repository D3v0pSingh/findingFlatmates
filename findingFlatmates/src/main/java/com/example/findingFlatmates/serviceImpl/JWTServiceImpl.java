package com.example.findingFlatmates.serviceImpl;

import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.findingFlatmates.entities.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
@Service
public class JWTServiceImpl {
	
	private final String SECRET_KEY = "c42b8b81679e45a7ab4862ce57b7b87dafc764dae967b1eb561cdaae3a1cd54f";
	
	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}
	
	public boolean isValid(String token, UserDetails user) {
		String username = extractUsername(token);
		return (username.equals(user.getUsername())) && !isTokenExpired(token);
	}
	
	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
	
	private Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}
	
	//Stage 3 extracting a particular claim.
	public<T> T extractClaim(String token, Function<Claims, T> resolver) {
		Claims claims =  extractAllClaims(token);
		return resolver.apply(claims);
	}
	
	//Stage two extracting all claims from token
	private Claims extractAllClaims(String token) {
		return Jwts
				.parser()
				.verifyWith(getSigninKey())
				.build()
				.parseSignedClaims(token)
				.getPayload();
	}
	
	//Stage one contains 2 methods : generateToken, getSigninKey
	public String generateToken(User user) {
		String token = Jwts
							.builder()
							.subject(user.getEmail())
							.issuedAt(new Date(System.currentTimeMillis()))
							.expiration(new Date(System.currentTimeMillis() + 24*60*60*1000))
							.signWith(getSigninKey())
							.compact();
		
		return token;
	}
	
	private SecretKey getSigninKey() {
		byte[] keyBytes = Decoders.BASE64URL.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}

}
