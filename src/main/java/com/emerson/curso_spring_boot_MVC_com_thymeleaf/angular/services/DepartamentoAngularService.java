package com.emerson.curso_spring_boot_MVC_com_thymeleaf.angular.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emerson.curso_spring_boot_MVC_com_thymeleaf.angular.exceptions.services.DataIntegrityExceptionPersonalized;
import com.emerson.curso_spring_boot_MVC_com_thymeleaf.angular.exceptions.services.ObjectNotFoundExceptionPersonalized;
import com.emerson.curso_spring_boot_MVC_com_thymeleaf.angular.repositories.DepartamentoAngularRepository;
import com.emerson.curso_spring_boot_MVC_com_thymeleaf.domain.Departamento;

@Service
public class DepartamentoAngularService {

	@Autowired
	private DepartamentoAngularRepository repo;

	public Departamento find(Long pId) {

		Optional<Departamento> obj = repo.findById(pId);

		return obj.orElseThrow(() -> new ObjectNotFoundExceptionPersonalized("Objeto não encontrado! Id: " +pId + ", Tipo: " + Departamento.class.getName()));
	}

	@Transactional
	public Departamento insert(Departamento obj) {
		obj.setId(null);

		Departamento objByNome = repo.findByNome(obj.getNome());

		// verifica se ja existe um objeto cadastrado com o mesmo nome
		if (objByNome == null) {
			obj = repo.save(obj);
			return obj;
		} else {
			throw new DataIntegrityExceptionPersonalized(
					"Já existe um departamento '" + objByNome.getNome() + "' cadastrado.");
		}
	}

	public Departamento update(Departamento obj) {
		Departamento newObj = find(obj.getId());
		Departamento objByNome = repo.findByNome(obj.getNome());
		
		// verifica se ja existe um objeto cadastrado com o mesmo nome
		if (objByNome == null) {
			updateData(newObj, obj);
			return repo.save(newObj);
		} else {
			throw new DataIntegrityExceptionPersonalized(
					"Já existe um departamento '" + objByNome.getNome() + "' cadastrado.");
		}
	}

	private void updateData(Departamento newObj, Departamento obj) {
		newObj.setNome(obj.getNome());
	}

	public void delete(Long id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityExceptionPersonalized("Não é possível excluir porque ha cargos relacionados.");
		}

	}

	public List<Departamento> findAll() {
		return repo.findAll();
	}
}
