package com.emerson.curso_spring_boot_MVC_com_thymeleaf.web.validator;

import java.time.LocalDate;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.emerson.curso_spring_boot_MVC_com_thymeleaf.domain.Funcionario;

/**
 * aula 60
 * criação desta classe para validar regras de negocio
 * do dominio Funcionario (pode ser adaptavel a outras entidades)
 * 
 * = = = = = IMPORTANTE = = = = =
 * 
 * na regra abordada na aula, nao podemos
 * adicionar um funcionario na qual a DATA DE SAIDA
 * seja menor que a DATA DE ENTRADA 
 * 
 * 
 * = = = = = = IMPORTANTE PARA REGRAS DE NEGÓCIO  = = = = = = 
 * 
 * a validação pode ser feita em qualquer um dos campos HTML
 * de acordo com a regra de negócio que for necessária para aplicação
 * 
 *  */
public class FuncionarioValidator implements Validator{
	
	/**
	 * @param clazz: é o objeto que vem lá do formulário frontend
	 * no documento HTML na qual sofrerá validações*/
	@Override
	public boolean supports(Class<?> clazz) {
		//verifica se realmente é um objeto do tipo Funcionario
		//caso sim, retorna true e chama o metodo validate()
		return Funcionario.class.equals(clazz) ;
	}
	
	
	
	@Override
	public void validate(Object object, Errors errors) {
		
		Funcionario f = (Funcionario) object;
		
		//recupera a data de entrada recebida da view HTML cadastro Funcionario
		LocalDate dataEntrada = f.getDataEntrada();
		
		//como o campo dataSaida não é obrigatório
		//só vai validar caso ele seja preenchido
		if (f.getDataSaida() != null) {
			//verifica se a data de saida é anterior a data de entrada
			if (f.getDataSaida().isBefore(dataEntrada)) {
				//a string dataSaida é o nome do campo no doc HTML que sera validado
				//a chave "PosteriorDataEntrada.funcionario.dataSaida" está vinculado ao arquivo "messages.properties"
				errors.rejectValue("dataSaida", "PosteriorDataEntrada.funcionario.dataSaida");
			}
		}
		
	}

}
