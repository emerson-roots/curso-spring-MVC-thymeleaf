package com.emerson.curso_spring_boot_MVC_com_thymeleaf.web.conversor;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

//aula 61 - valida o numero do endereço para proibir inserir letras
@Component
public class StringToInteger implements Converter<String, Integer> {

	@Override
	public Integer convert(String text) {

		// remnove espaços em branco
		text.trim();

		if (text.matches("[0-9]+")) {
			return Integer.valueOf(text);
		}

		return null;
	}

}
