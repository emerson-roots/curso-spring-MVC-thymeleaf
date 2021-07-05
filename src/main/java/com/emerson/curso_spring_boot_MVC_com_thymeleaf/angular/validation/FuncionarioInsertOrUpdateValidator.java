package com.emerson.curso_spring_boot_MVC_com_thymeleaf.angular.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.emerson.curso_spring_boot_MVC_com_thymeleaf.angular.dto.FuncionarioDTO;
import com.emerson.curso_spring_boot_MVC_com_thymeleaf.angular.exceptions.resources.FieldMessagePersonalized;
import com.emerson.curso_spring_boot_MVC_com_thymeleaf.angular.repositories.FuncionarioAngularRepository;
import com.emerson.curso_spring_boot_MVC_com_thymeleaf.domain.Funcionario;

public class FuncionarioInsertOrUpdateValidator implements ConstraintValidator<FuncionarioInsertOrUpdate, FuncionarioDTO> {

	@Autowired
	private FuncionarioAngularRepository repo;
	
	@Autowired
	private HttpServletRequest request;

	@Override
	public void initialize(FuncionarioInsertOrUpdate ann) {
	}

	@Override
	public boolean isValid(FuncionarioDTO objDTO, ConstraintValidatorContext context) {
		List<FieldMessagePersonalized> list = new ArrayList<>();
		
		Funcionario aux = repo.findByNome(objDTO.getNome());
		
		//responsável por captar parametros recebidos pela URI
		@SuppressWarnings("unchecked")
		Map<String,String> map = (Map<String,String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Long uriId = null;
		
		// se não vier parametro na URL gera erro
		if(!map.isEmpty()) {
			uriId = Long.parseLong(map.get("id"));
		}
		
		if (aux != null && !aux.getId().equals(uriId)) {
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