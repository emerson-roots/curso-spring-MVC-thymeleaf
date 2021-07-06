package com.emerson.curso_spring_boot_MVC_com_thymeleaf.angular.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emerson.curso_spring_boot_MVC_com_thymeleaf.angular.domain.Usuario;
import com.emerson.curso_spring_boot_MVC_com_thymeleaf.angular.dto.UsuarioDTO;
import com.emerson.curso_spring_boot_MVC_com_thymeleaf.angular.dto.UsuarioNewDTO;
import com.emerson.curso_spring_boot_MVC_com_thymeleaf.angular.repositories.UsuarioAngularRepository;

@Service
public class UsuarioAngularService {

	@Autowired
	private UsuarioAngularRepository repo;

	public Usuario insert(Usuario obj) {
		obj.setId(null);
		obj = repo.save(obj);
		return obj;
	}

	public List<Usuario> findAll() {
		return repo.findAll();
	}

	public Usuario fromDTO(UsuarioDTO objDto) {
		return new Usuario(objDto.getId(), objDto.getEmail(), null, null);
	}

	// sobrecarga do metodo só que agora recebendo um ClienteNewDTO
	public Usuario fromDTO(UsuarioNewDTO objDto) {
		Usuario user = new Usuario(null, objDto.getNome(), objDto.getEmail(), objDto.getSenha());
		return user;
	}
}
