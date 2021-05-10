package com.emerson.curso_spring_boot_MVC_com_thymeleaf.web.conversor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.emerson.curso_spring_boot_MVC_com_thymeleaf.domain.Cargo;
import com.emerson.curso_spring_boot_MVC_com_thymeleaf.service.CargoService;


//aula 50 porém a primeira implementação de um converter foi na aula 44
//em caso de dúvidas, revisar aula 44
@Component
public class StringToCargoConverter implements Converter<String, Cargo> {

	@Autowired
	private CargoService service;

	/**
	 * @param text: através dela recebemos do frontend o "id" em formato de string
	 *              da entidade departamento e através dela convertemos para o tipo
	 *              Cargo
	 */
	@Override
	public Cargo convert(String text) {
		// como a classe é chamada sempre que a classe Controller é solicitado
		// é necessário tomar cuidados com exceptions
		if (text.isEmpty()) {
			return null;
		}

		// converte o id/string recebido do front em id/long
		Long id = Long.valueOf(text);

		// retorna o objeto convertido
		return service.buscarPorId(id);
	}

}
