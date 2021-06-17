package com.emerson.curso_spring_boot_MVC_com_thymeleaf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emerson.curso_spring_boot_MVC_com_thymeleaf.dao.DepartamentoDao;
import com.emerson.curso_spring_boot_MVC_com_thymeleaf.domain.Departamento;
import com.emerson.curso_spring_boot_MVC_com_thymeleaf.exceptions.services.DataIntegrityExceptionPersonalized;

//aula 26 - foi abordado uma forma diferente de uso do transactional
//apenas para mostrar outra forma de uso da anotação
//na aula foi utilizado a anotação somente nos metodos
//de leitura, invés de usar diretamente na assinatura da classe
@Service
@Transactional
public class DepartamentoServiceImpl implements DepartamentoService {

	@Autowired
	private DepartamentoDao dao;

	@Override
	public void salvar(Departamento departamento) {
		dao.save(departamento);
	}

	@Override
	public void editar(Departamento departamento) {
		dao.update(departamento);

	}

	@Override
	public void excluir(Long id) {
		dao.delete(id);
	}

	// aula 26 - foi abordado uma forma diferente de uso do transactional
	// apenas para mostrar outra forma de uso da anotação
	// na aula foi utilizado a anotação somente nos metodos
	// de leitura, invés de usar diretamente na assinatura da classe
	@Override
	@Transactional(readOnly = true)
	public Departamento buscarPorId(Long id) {
		return dao.findById(id);
	}

	// aula 26 - foi abordado uma forma diferente de uso do transactional
	// apenas para mostrar outra forma de uso da anotação
	// na aula foi utilizado a anotação somente nos metodos
	// de leitura, invés de usar diretamente na assinatura da classe
	@Override
	@Transactional(readOnly = true)
	public List<Departamento> buscarTodos() {
		return dao.findAll();
	}

	// aula 39
	// verifica se o departamento possui cargos relacionados
	@Override
	public boolean departamentoTemCargos(Long id) {

		if (buscarPorId(id).getCargos().isEmpty()) {
			return false;
		}
		return true;
	}

	/*
	 * IMPLEMENTAÇÕES PARA TRABALHAR COM ANGULAR
	 * 
	 **/

	//
	@Override
	public void delete(Long id) {
		try {
			if (!departamentoTemCargos(id)) {
				dao.delete(id);
			} else {
				throw new DataIntegrityExceptionPersonalized("Não é possível excluir porque ha cargos relacionados");
			}
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityExceptionPersonalized("Não é possível excluir porque ha cargos relacionados" + e);
		}

	}
}
