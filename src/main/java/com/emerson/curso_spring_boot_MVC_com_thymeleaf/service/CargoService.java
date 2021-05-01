package com.emerson.curso_spring_boot_MVC_com_thymeleaf.service;

import java.util.List;

import com.emerson.curso_spring_boot_MVC_com_thymeleaf.domain.Cargo;

public interface CargoService {

	void salvar(Cargo cargo);
	
	void editar(Cargo cargo);
	
	void excluir(Long id);
	
	Cargo buscarPorId(Long id);
	
	List<Cargo> buscarTodos();
}
