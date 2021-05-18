package com.emerson.curso_spring_boot_MVC_com_thymeleaf.service;

import java.util.List;

import com.emerson.curso_spring_boot_MVC_com_thymeleaf.domain.Cargo;
import com.emerson.curso_spring_boot_MVC_com_thymeleaf.util.PaginacaoUtil;

public interface CargoService {

	void salvar(Cargo cargo);
	
	void editar(Cargo cargo);
	
	void excluir(Long id);
	
	Cargo buscarPorId(Long id);
	
	List<Cargo> buscarTodos();
	
	//aula 47
	boolean cargoTemFuncionarios(Long id);
	
	//aula 82
	PaginacaoUtil<Cargo> buscaPorPagina(int pagina);
}
