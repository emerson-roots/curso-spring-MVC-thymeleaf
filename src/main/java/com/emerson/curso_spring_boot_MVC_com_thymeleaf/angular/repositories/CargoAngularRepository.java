package com.emerson.curso_spring_boot_MVC_com_thymeleaf.angular.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.emerson.curso_spring_boot_MVC_com_thymeleaf.domain.Cargo;


@Repository
public interface CargoAngularRepository extends JpaRepository<Cargo, Long> {

	@Transactional(readOnly = true)
	Cargo findByNome(String nome);

}
