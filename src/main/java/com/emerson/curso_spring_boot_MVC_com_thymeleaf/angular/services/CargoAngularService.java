package com.emerson.curso_spring_boot_MVC_com_thymeleaf.angular.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emerson.curso_spring_boot_MVC_com_thymeleaf.angular.exceptions.services.DataIntegrityExceptionPersonalized;
import com.emerson.curso_spring_boot_MVC_com_thymeleaf.angular.exceptions.services.ObjectNotFoundExceptionPersonalized;
import com.emerson.curso_spring_boot_MVC_com_thymeleaf.angular.repositories.CargoAngularRepository;
import com.emerson.curso_spring_boot_MVC_com_thymeleaf.domain.Cargo;

@Service
public class CargoAngularService {

	@Autowired
	private CargoAngularRepository repo;

	public Cargo find(Long pId) {

		Optional<Cargo> obj = repo.findById(pId);

		return obj.orElseThrow(() -> new ObjectNotFoundExceptionPersonalized("Objeto não encontrado! Id: " +pId + ", Tipo: " + Cargo.class.getName()));
	}

	@Transactional
	public Cargo insert(Cargo obj) {
		obj.setId(null);

		Cargo objByNome = repo.findByNome(obj.getNome());

		// verifica se ja existe um objeto cadastrado com o mesmo nome
		if (objByNome == null) {
			obj = repo.save(obj);
			return obj;
		} else {
			throw new DataIntegrityExceptionPersonalized(
					"Já existe um Cargo '" + objByNome.getNome() + "' cadastrado.");
		}
	}

	public Cargo update(Cargo obj) {
		Cargo newObj = find(obj.getId());
		Cargo objByNome = repo.findByNome(obj.getNome());
		
		// verifica se ja existe um objeto cadastrado com o mesmo nome
		if (objByNome == null) {
			updateData(newObj, obj);
			return repo.save(newObj);
		} else {
			throw new DataIntegrityExceptionPersonalized(
					"Já existe um Cargo '" + objByNome.getNome() + "' cadastrado.");
		}
	}

	private void updateData(Cargo newObj, Cargo obj) {
		newObj.setNome(obj.getNome());
		newObj.setDepartamento(obj.getDepartamento());
	}

	public void delete(Long id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityExceptionPersonalized("Não é possível excluir porque ha funcionários relacionados.");
		}

	}

	public List<Cargo> findAll() {
		return repo.findAll();
	}
	
	public Page<Cargo> findPage(Integer pPage, Integer pLinesPerPage, String pOrderBy,
			String pDirectionOrdenation) {
		// PageRequest do Spring Data
		PageRequest pageRequest = PageRequest.of(pPage, pLinesPerPage, Direction.valueOf(pDirectionOrdenation),
				pOrderBy);
		return repo.findAll(pageRequest);
	}
}
