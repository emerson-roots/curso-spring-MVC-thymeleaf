package com.emerson.curso_spring_boot_MVC_com_thymeleaf.angular.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.emerson.curso_spring_boot_MVC_com_thymeleaf.angular.security.UserSS;

public class UserService {

	// método que retorna um usuario logado na forma de um usuário do spring
	// security
	public static UserSS authenticated() {

		// tratamento de excessão - pode gerar excessão caso o metodo
		// SecurityContextHolder retorne um usuário inexistente no banco
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}

	}

}
