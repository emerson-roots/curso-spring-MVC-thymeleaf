package com.emerson.curso_spring_boot_MVC_com_thymeleaf.angular.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.emerson.curso_spring_boot_MVC_com_thymeleaf.angular.domain.Usuario;
import com.emerson.curso_spring_boot_MVC_com_thymeleaf.angular.repositories.UsuarioAngularRepository;
import com.emerson.curso_spring_boot_MVC_com_thymeleaf.angular.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioAngularRepository repo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Usuario usr = repo.findByEmail(email);

		if (usr == null) {
			throw new UsernameNotFoundException(email);
		}

		return new UserSS(usr.getId(), usr.getEmail(), usr.getSenha(), usr.getPerfis());
	}

}