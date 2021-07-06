package com.emerson.curso_spring_boot_MVC_com_thymeleaf.angular.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emerson.curso_spring_boot_MVC_com_thymeleaf.angular.domain.Usuario;


@Repository
public interface UsuarioAngularRepository extends JpaRepository<Usuario, Long> {

}
