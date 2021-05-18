package com.emerson.curso_spring_boot_MVC_com_thymeleaf.dao;

import java.util.List;

import com.emerson.curso_spring_boot_MVC_com_thymeleaf.domain.Cargo;
import com.emerson.curso_spring_boot_MVC_com_thymeleaf.util.PaginacaoUtil;

public interface CargoDao {
	
	void save(Cargo cargo);
	
	void update(Cargo cargo);
	
	void delete(Long id);
	
	Cargo findById(Long id);
	
	List<Cargo> findAll();
	
	//aula 82
	PaginacaoUtil<Cargo> buscaPaginada(int pagina);
	
}
