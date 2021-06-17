package com.emerson.curso_spring_boot_MVC_com_thymeleaf.service;

import java.util.List;

import com.emerson.curso_spring_boot_MVC_com_thymeleaf.domain.Departamento;

public interface DepartamentoService {

	void salvar(Departamento departamento);

	void editar(Departamento departamento);

	void excluir(Long id);

	Departamento buscarPorId(Long id);

	List<Departamento> buscarTodos();
	
	//aula 39, verifica se departamento contém cargos antes de excluir
	boolean departamentoTemCargos(Long id);
	
	// =================================================
	// metodos usados para angular
	// =================================================
	void delete(Long id);

}
