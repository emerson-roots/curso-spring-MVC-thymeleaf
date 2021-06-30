package com.emerson.curso_spring_boot_MVC_com_thymeleaf.angular.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.emerson.curso_spring_boot_MVC_com_thymeleaf.angular.dto.FuncionarioNewDTO;
import com.emerson.curso_spring_boot_MVC_com_thymeleaf.angular.repositories.FuncionarioAngularRepository;
import com.emerson.curso_spring_boot_MVC_com_thymeleaf.domain.Funcionario;
import com.emerson.curso_spring_boot_MVC_com_thymeleaf.exceptions.resources.FieldMessagePersonalized;

public class FuncionarioInsertValidator implements ConstraintValidator<FuncionarioInsert, FuncionarioNewDTO> {

	@Autowired
	private FuncionarioAngularRepository repo;

	@Override
	public void initialize(FuncionarioInsert ann) {
	}

	@Override
	public boolean isValid(FuncionarioNewDTO objDTO, ConstraintValidatorContext context) {
		List<FieldMessagePersonalized> list = new ArrayList<>();
		
		Funcionario aux = repo.findByNome(objDTO.getNome());
		
		if (aux != null) {
			list.add(new FieldMessagePersonalized("nome","Já existe um funcionario com nome '" + objDTO.getNome() + "' cadastrado."));
		}
		
		if(objDTO.getDataSaida() != null && objDTO.getDataSaida().isBefore(objDTO.getDataEntrada())) {
			list.add(new FieldMessagePersonalized("dataSaida","A data de saida não pode ser anterior a data de entrada."));
		}

		// itera os erros adicionando na lista
		for (FieldMessagePersonalized e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}