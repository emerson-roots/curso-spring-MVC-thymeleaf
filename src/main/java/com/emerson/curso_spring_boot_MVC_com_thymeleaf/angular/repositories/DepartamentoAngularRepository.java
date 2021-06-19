package com.emerson.curso_spring_boot_MVC_com_thymeleaf.angular.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.emerson.curso_spring_boot_MVC_com_thymeleaf.domain.Departamento;

public interface DepartamentoAngularRepository extends JpaRepository<Departamento, Long> {

	@Transactional(readOnly = true)
	Departamento findByNome(String nome);

}
