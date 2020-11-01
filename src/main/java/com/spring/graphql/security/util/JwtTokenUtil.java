package com.spring.graphql.security.util;

import com.spring.graphql.security.UserDetailsImpl;
import io.jsonwebtoken.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class JwtTokenUtil  {

	@Value("${jwt.secret}")
	String jwtSecret;

	@Value("${jwt.expirationms}")
	Long jwtExpirationMs;

	final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;


	public String generateJwtToken(UserDetailsImpl userPrincipal) {
		return Jwts.builder()
				.setSubject((String.valueOf(userPrincipal.getId())))
				.setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
				.signWith(SIGNATURE_ALGORITHM, jwtSecret)
				.compact();
	}

	public String getUserIdFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	}

	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException e) {
			log.error("Invalid JWT signature: {}", e.getMessage());
		} catch (MalformedJwtException e) {
			log.error("Invalid JWT token: {}", e.getMessage());
		} catch (ExpiredJwtException e) {
			log.error("JWT token is expired: {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
			log.error("JWT token is unsupported: {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			log.error("JWT claims string is empty: {}", e.getMessage());
		}

		return false;
	}
}