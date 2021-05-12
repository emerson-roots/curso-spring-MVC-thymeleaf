package com.emerson.curso_spring_boot_MVC_com_thymeleaf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.emerson.curso_spring_boot_MVC_com_thymeleaf.domain.Funcionario;

//aula 22
@Repository
public class FuncionarioDaoImpl extends AbstractDao<Funcionario, Long> implements FuncionarioDao {

	// aula 53
	@Override
	public List<Funcionario> findByNome(String nome) {

		// este retorno só é possivel devido a extensão a classe AbstractDao
		// classe implementada na aula 21 onde foi criado também o
		// método createQuery
		return createQuery("select f from Funcionario f where f.nome like concat('%',?1,'%') ", nome);
	}
	
	// aula 53
	@Override
	public List<Funcionario> findByCargoId(Long id) {

		// este retorno só é possivel devido a extensão a classe AbstractDao
		// classe implementada na aula 21 onde foi criado também o
		// método createQuery
		return createQuery("select f from Funcionario f where f.cargo.id like ?1", id);
	}

}
