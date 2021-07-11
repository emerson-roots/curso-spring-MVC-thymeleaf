package com.emerson.curso_spring_boot_MVC_com_thymeleaf.angular.config;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * classe auxiliar para tratamento da exception AccessDenied trabalha
 * diretamente com a classe security config
 */
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Bean
	public static AuthenticationEntryPoint authenticationEntryPoint() {
		return new CustomAuthenticationEntryPoint();
	}

	@Override
	public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException authException)
			throws IOException, ServletException {
		res.setContentType("application/json");
		res.setStatus(403);
		res.getWriter().append(json(req.getRequestURI()));
	}

	private String json(String path) {
		long date = new Date().getTime();
		return "{\"timestamp\": " + date + ", " + "\"status\": 403, " + "\"error\": \"Acesso negado\", "
				+ "\"message\": \"Você precisa estar logado para acessar este recurso.\", "
				+ "\"path\": \"" + path + "\"}";
	}

}
