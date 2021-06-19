package com.emerson.curso_spring_boot_MVC_com_thymeleaf.angular.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emerson.curso_spring_boot_MVC_com_thymeleaf.angular.repositories.DepartamentoAngularRepository;
import com.emerson.curso_spring_boot_MVC_com_thymeleaf.domain.Departamento;
import com.emerson.curso_spring_boot_MVC_com_thymeleaf.exceptions.services.DataIntegrityExceptionPersonalized;

@Service
public class DepartamentoAngularService {

	@Autowired
	private DepartamentoAngularRepository repo;

	public Departamento find(Long pId) {

		Optional<Departamento> obj = repo.findById(pId);

		return obj.orElseThrow(() -> null);//-> new ObjectNotFoundExceptionPersonalized("Objeto não encontrado! Id: " + pId + ", Tipo: " + Departamento.class.getName()));
	}

	@Transactional
	public Departamento insert(Departamento obj) {
		obj.setId(null);
		obj = repo.save(obj);
		return obj;
	}

	public Departamento update(Departamento obj) {
		Departamento newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
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
