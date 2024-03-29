package com.openclassrooms.mddapi.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;
import static java.lang.String.format;

import java.util.Date;

@Slf4j
@Service
public class JwtService {

	@Value("${jwt.secret.key}")
	private String jwtKey;

	private final String jwtIssuer = "fr.herve";

	public String generateToken(User user) {
		return Jwts.builder().setSubject(format("%s", user.getUsername())).setIssuer(jwtIssuer).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000))
				.signWith(SignatureAlgorithm.HS256, jwtKey).compact();
	}

	public String getUsername(String token) {
		Claims claims = Jwts.parser().setSigningKey(jwtKey).parseClaimsJws(token).getBody();
		return claims.getSubject().split(",")[0];
	}

	public Date getExpirationDate(String token) {
		Claims claims = Jwts.parser().setSigningKey(jwtKey).parseClaimsJws(token).getBody();
		return claims.getExpiration();
	}

	public boolean validate(String token) {
		try {
			Jwts.parser().setSigningKey(jwtKey).parseClaimsJws(token);
			return true;
		} catch (SignatureException e) {
			log.error("Invalid JWT signature - {}", e.getMessage());
		} catch (MalformedJwtException e) {
			log.error("Invalid JWT token - {}", e.getMessage());
		} catch (ExpiredJwtException e) {
			log.error("Expired JWT token - {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
			log.error("Unsupported JWT token - {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			log.error("JWT claims string is empty - {}", e.getMessage());
		}
		return false;
	}

}
