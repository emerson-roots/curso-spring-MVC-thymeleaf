package com.emerson.curso_spring_boot_MVC_com_thymeleaf.dao;

import java.time.LocalDate;
import java.util.List;

import com.emerson.curso_spring_boot_MVC_com_thymeleaf.domain.Funcionario;

public interface FuncionarioDao {

	void save(Funcionario funcionario);

	void update(Funcionario funcionario);

	void delete(Long id);

	Funcionario findById(Long id);

	List<Funcionario> findAll();

	// aula 53
	List<Funcionario> findByNome(String nome);

	// aula 54
	List<Funcionario> findByCargoId(Long id);

	// aula 55
	List<Funcionario> findByDataEntradaDataSaida(LocalDate entrada, LocalDate saida);

	// aula 55
	List<Funcionario> findByDataEntrada(LocalDate entrada);

	// aula 55
	List<Funcionario> findByDataSaida(LocalDate saida);

}
