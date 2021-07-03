package com.emerson.curso_spring_boot_MVC_com_thymeleaf.angular.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emerson.curso_spring_boot_MVC_com_thymeleaf.angular.dto.FuncionarioDTO;
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
	
	@Transactional(readOnly = true)
	public List<Funcionario> findAllByName(String nome){
		return repo.findByNomeIgnoreCaseContaining(nome);
	}
	
	@Transactional(readOnly = true)
	public List<Funcionario> findAllByCargo(Long id){
		return repo.findByCargoId(id);
	}
	
	@Transactional(readOnly = true)
	public List<Funcionario> findAllByDataEntradaAndDataSaida(LocalDate dataEntrada, LocalDate dataSaida){
		return repo.findByDataEntradaBetween(dataEntrada, dataSaida);
	}
	
	@Transactional(readOnly = true)
	public List<Funcionario> findAllByDataEntrada(LocalDate dataEntrada){
		return repo.findByDataEntrada(dataEntrada);
	}
	
	@Transactional(readOnly = true)
	public List<Funcionario> findAllByDataSaida(LocalDate dataSaida){
		return repo.findByDataSaida(dataSaida);
	}

	@Transactional
	public Funcionario insert(Funcionario obj) {
		obj.setId(null);

		obj = repo.save(obj);
		return obj;
	}
	
	public Funcionario fromDTO(FuncionarioDTO objDto) {
		
		Funcionario funcionario = new Funcionario(
				objDto.getId(),
				objDto.getNome(),
				objDto.getSalario(), 
				objDto.getDataEntrada(), 
				objDto.getDataSaida(), 
				objDto.getEndereco(), 
				objDto.getCargo());
		
		return funcionario;
	}

	public Funcionario update(Funcionario funcionario) {
		Funcionario newObj = find(funcionario.getId());
//		Funcionario objByNome = repo.findByNome(obj.getNome());

		// verifica se ja existe um objeto cadastrado com o mesmo nome
//		if (objByNome == null) {
			updateData(newObj, funcionario);
			return repo.save(newObj);
//		} else {
//			throw new DataIntegrityExceptionPersonalized(
//					"Já existe um Funcionario '" + objByNome.getNome() + "' cadastrado.");
//		}
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
