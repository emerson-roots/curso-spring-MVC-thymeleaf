package com.emerson.curso_spring_boot_MVC_com_thymeleaf.angular.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private Long expiration;

	public String generateToken(String username) {
		return Jwts.builder().setSubject(username).setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(SignatureAlgorithm.HS512, secret.getBytes()).compact();
	}

	public boolean tokenValido(String token) {

		Claims claims = getClaims(token);

		if (claims != null) {
			String username = claims.getSubject();
			Date expirationDate = claims.getExpiration();
			Date now = new Date(System.currentTimeMillis());

			if (username != null && expirationDate != null && now.before(expirationDate)) {
				return true;
			}

		}
		// se algo falhar retorna falso
		return false;
	}

	// pega o usuario a partir do token
	public String getUsername(String token) {

		// Claimns é um tipo do JWT que armazena as reivindicações do token
		Claims claims = getClaims(token);
		if (claims != null) {
			return claims.getSubject();
		}
		return null;
	}

	// função que recupera os Claimns a partir de um token
	private Claims getClaims(String token) {

		// tenta pegar o claimns do token
		// caso seja invalido ou de problemas, retorna nulo
		try {
			return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			return null;
		}

	}

}
