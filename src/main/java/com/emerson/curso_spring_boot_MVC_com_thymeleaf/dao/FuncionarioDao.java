package com.emerson.curso_spring_boot_MVC_com_thymeleaf.dao;

import java.util.List;

import com.emerson.curso_spring_boot_MVC_com_thymeleaf.domain.Funcionario;

public interface FuncionarioDao {
	
	void save(Funcionario funcionario);
	
	void update(Funcionario funcionario);
	
	void delete(Long id);
	
	Funcionario findById(Long id);
	
	List<Funcionario> findAll();

	List<Funcionario> findByNome(String nome);
	
}
