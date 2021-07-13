package com.emerson.curso_spring_boot_MVC_com_thymeleaf.angular.resources;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
public class AuthResource {
	
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

}
