package com.emerson.curso_spring_boot_MVC_com_thymeleaf.service;

import java.time.LocalDate;
import java.util.List;

import com.emerson.curso_spring_boot_MVC_com_thymeleaf.domain.Funcionario;

public interface FuncionarioService {

	void salvar(Funcionario funcionario);

	void editar(Funcionario funcionario);

	void excluir(Long id);

	Funcionario buscarPorId(Long id);

	List<Funcionario> buscarTodos();
	
	//aula 53
	List<Funcionario> buscarPorNome(String nome);
	
	//aula 54
	List<Funcionario> buscarPorCargo(Long id);
	
	//aula 55
	List<Funcionario> buscarPorDatas(LocalDate entrada, LocalDate saida);

}
