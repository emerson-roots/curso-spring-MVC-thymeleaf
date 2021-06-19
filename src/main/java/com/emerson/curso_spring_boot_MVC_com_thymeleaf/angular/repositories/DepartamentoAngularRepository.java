package com.emerson.curso_spring_boot_MVC_com_thymeleaf.angular.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emerson.curso_spring_boot_MVC_com_thymeleaf.domain.Departamento;

public interface DepartamentoAngularRepository extends JpaRepository<Departamento, Long> {
	
}
