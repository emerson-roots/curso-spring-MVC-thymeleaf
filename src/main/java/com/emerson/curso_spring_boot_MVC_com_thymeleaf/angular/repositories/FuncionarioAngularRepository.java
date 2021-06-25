package com.emerson.curso_spring_boot_MVC_com_thymeleaf.angular.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.emerson.curso_spring_boot_MVC_com_thymeleaf.domain.Funcionario;


@Repository
public interface FuncionarioAngularRepository extends JpaRepository<Funcionario, Long> {

	@Transactional(readOnly = true)
	Funcionario findByNome(String nome);

}
