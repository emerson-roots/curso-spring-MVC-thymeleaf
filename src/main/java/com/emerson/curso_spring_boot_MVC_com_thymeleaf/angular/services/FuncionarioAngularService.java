package com.emerson.curso_spring_boot_MVC_com_thymeleaf.angular.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emerson.curso_spring_boot_MVC_com_thymeleaf.angular.repositories.FuncionarioAngularRepository;
import com.emerson.curso_spring_boot_MVC_com_thymeleaf.domain.Funcionario;
import com.emerson.curso_spring_boot_MVC_com_thymeleaf.exceptions.services.DataIntegrityExceptionPersonalized;
import com.emerson.curso_spring_boot_MVC_com_thymeleaf.exceptions.services.ObjectNotFoundExceptionPersonalized;

@Service
public class FuncionarioAngularService {

	@Autowired
	private FuncionarioAngularRepository repo;

	public Funcionario find(Long pId) {

		Optional<Funcionario> obj = repo.findById(pId);

		return obj.orElseThrow(() -> new ObjectNotFoundExceptionPersonalized(
				"Objeto não encontrado! Id: " + pId + ", Tipo: " + Funcionario.class.getName()));
	}

	@Transactional
	public Funcionario insert(Funcionario obj) {
		obj.setId(null);

		Funcionario objByNome = repo.findByNome(obj.getNome());

		// verifica se ja existe um objeto cadastrado com o mesmo nome
		if (objByNome == null) {
			obj = repo.save(obj);
			return obj;
		} else {
			throw new DataIntegrityExceptionPersonalized(
					"Já existe um Funcionario '" + objByNome.getNome() + "' cadastrado.");
		}
	}

	public Funcionario update(Funcionario obj) {
		Funcionario newObj = find(obj.getId());
		Funcionario objByNome = repo.findByNome(obj.getNome());

		// verifica se ja existe um objeto cadastrado com o mesmo nome
		if (objByNome == null) {
			updateData(newObj, obj);
			return repo.save(newObj);
		} else {
			throw new DataIntegrityExceptionPersonalized(
					"Já existe um Funcionario '" + objByNome.getNome() + "' cadastrado.");
		}
	}

	private void updateData(Funcionario newObj, Funcionario obj) {
		newObj.setNome(obj.getNome());
		newObj.setSalario(obj.getSalario());
		newObj.setDataEntrada(obj.getDataEntrada());
		newObj.setDataSaida(obj.getDataSaida());
		newObj.setEndereco(obj.getEndereco());
		newObj.setCargo(obj.getCargo());
	}

	public void delete(Long id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityExceptionPersonalized("Não é possível excluir o funcionario.");
		}

	}

	public List<Funcionario> findAll() {
		return repo.findAll();
	}
}
