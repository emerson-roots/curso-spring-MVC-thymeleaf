package com.emerson.curso_spring_boot_MVC_com_thymeleaf.web.conversor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.emerson.curso_spring_boot_MVC_com_thymeleaf.domain.Departamento;
import com.emerson.curso_spring_boot_MVC_com_thymeleaf.service.DepartamentoService;

//aula 44
//esta classe recebe Converter<String,Departamento> 02 objetos
//sendo String: o objéto que vem do frontend, no caso será um id em formato de string
//e o segundo é um objeto departamento, que é o tipo de objeto que iramos retornar para o controller
//
//esta classe chamada SEMPRE que a classe CargoController for solicitada.
@Component
public class StringToDepartamentoConverter implements Converter<String, Departamento> {

	@Autowired
	private DepartamentoService service;

	/**
	 * @param text: através dela recebemos do frontend o "id" em formato de string
	 *              da entidade departamento e através dela convertemos para o tipo
	 *              Departamento
	 */
	@Override
	public Departamento convert(String text) {
		// como a classe é chamada sempre que a classe CargoController é solicitada
		// é necessário tomar cuidados com exceptions
		if (text.isEmpty()) {
			return null;
		}

		// converte o id/string recebido do front em id/long
		Long id = Long.valueOf(text);

		// retorna o departamento convertido
		return service.buscarPorId(id);
	}

}
