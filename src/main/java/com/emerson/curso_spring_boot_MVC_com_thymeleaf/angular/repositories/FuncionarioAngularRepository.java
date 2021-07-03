package com.emerson.curso_spring_boot_MVC_com_thymeleaf.angular.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.emerson.curso_spring_boot_MVC_com_thymeleaf.domain.Funcionario;

@Repository
public interface FuncionarioAngularRepository extends JpaRepository<Funcionario, Long> {

	@Transactional(readOnly = true)
	Funcionario findByNome(String nome);

	@Transactional(readOnly = true)
	List<Funcionario> findByNomeIgnoreCaseContaining(@Param("nome") String nome);

	@Transactional(readOnly = true)
	List<Funcionario> findByCargoId(@Param("id") Long id);

	@Transactional(readOnly = true)
	List<Funcionario> findByDataEntradaBetween(@Param("dataEntrada") LocalDate dataEntrada,
			@Param("dataSaida") LocalDate dataSaida);

	@Transactional(readOnly = true)
	List<Funcionario> findByDataEntrada(@Param("dataEntrada") LocalDate dataEntrada);

	@Transactional(readOnly = true)
	List<Funcionario> findByDataSaida(@Param("dataSaida") LocalDate dataSaida);

}
