package com.emerson.curso_spring_boot_MVC_com_thymeleaf.domain.dao;

import java.util.List;

import com.emerson.curso_spring_boot_MVC_com_thymeleaf.domain.Departamento;

//aula 22
public interface DepartamentoDao {
	
	void save(Departamento departamento);
	
	void update(Departamento departamento);
	
	void delete(Long id);
	
	Departamento findById(Long id);
	
	List<Departamento> findAll();
	
}
