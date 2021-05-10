package com.emerson.curso_spring_boot_MVC_com_thymeleaf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emerson.curso_spring_boot_MVC_com_thymeleaf.dao.FuncionarioDao;
import com.emerson.curso_spring_boot_MVC_com_thymeleaf.domain.Funcionario;

//aula 26 - foi abordado uma forma diferente de uso do transactional
//apenas para mostrar outra forma de uso da anotação
//na aula foi utilizado a anotação somente nos metodos
//de leitura, invés de usar diretamente na assinatura da classe
@Service
@Transactional(readOnly = true)
public class FuncionarioServiceImpl implements FuncionarioService {

	@Autowired
	private FuncionarioDao dao;
	
	@Transactional(readOnly = false)
	@Override
	public void salvar(Funcionario funcionario) {
		dao.save(funcionario);

	}
	
	@Transactional(readOnly = false)
	@Override
	public void editar(Funcionario funcionario) {
		dao.update(funcionario);
	}
	
	@Transactional(readOnly = false)
	@Override
	public void excluir(Long id) {
		dao.delete(id);
	}
	
	//aula 26 - foi abordado uma forma diferente de uso do transactional
	//apenas para mostrar outra forma de uso da anotação
	//na aula foi utilizado a anotação somente nos metodos
	//de leitura, invés de usar diretamente na assinatura da classe
	@Override
	@Transactional(readOnly = true)
	public Funcionario buscarPorId(Long id) {
		return dao.findById(id);
	}

	//aula 26 - foi abordado uma forma diferente de uso do transactional
	//apenas para mostrar outra forma de uso da anotação
	//na aula foi utilizado a anotação somente nos metodos
	//de leitura, invés de usar diretamente na assinatura da classe
	@Override
	@Transactional(readOnly = true)
	public List<Funcionario> buscarTodos() {
		return dao.findAll();
	}

}
