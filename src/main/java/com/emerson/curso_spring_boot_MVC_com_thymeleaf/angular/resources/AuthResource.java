package com.emerson.curso_spring_boot_MVC_com_thymeleaf.angular.resources;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.emerson.curso_spring_boot_MVC_com_thymeleaf.angular.security.JWTUtil;
import com.emerson.curso_spring_boot_MVC_com_thymeleaf.angular.security.UserSS;
import com.emerson.curso_spring_boot_MVC_com_thymeleaf.angular.services.UserService;

@RestController
@RequestMapping(value = "/auth")
public class AuthResource {
	
	@Autowired
	private JWTUtil jwtUtil;
	
	/**
	 * verifica se o usuario possui permissao para acessar determinado recurso
	 * no frontend 
	 * 
	 * dada a devida permissao, o frontend pode tratar de acordo enviando 
	 * um alert ao usuario, inativando o recurso ou redirecionando para uma pagina 
	 * de erro de permissao nao concedida
	 */
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/check_permission", method = RequestMethod.GET)
	public ResponseEntity<Void> checkPermission(HttpServletResponse response) {
		
		return ResponseEntity.noContent().build();
		
	}
	
	/**
	 * endpoint protegido por autenticação o usuario tem que estar logado caso
	 * contrario gera uma excessão Forbidden
	 */
	@RequestMapping(value = "refresh_token", method = RequestMethod.POST)
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {

		UserSS user = UserService.authenticated();

		String token = jwtUtil.generateToken(user.getUsername());

		response.addHeader("Authorization", "Bearer " + token);
		
		response.addHeader("access-control-expose-headers", "Authorization");

		return ResponseEntity.noContent().build();
	}

}
