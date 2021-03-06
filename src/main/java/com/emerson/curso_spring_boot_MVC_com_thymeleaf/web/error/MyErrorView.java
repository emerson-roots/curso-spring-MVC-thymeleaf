package com.emerson.curso_spring_boot_MVC_com_thymeleaf.web.error;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

//aula 66
@Component
public class MyErrorView implements ErrorViewResolver {

	@Override
	public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status, Map<String, Object> map) {
		
		// correção de bug na aula 69 - retirado a barra "/" do parametro "/error"
		ModelAndView model = new ModelAndView("error");

		// recupera o status referente ao erro e adiciona na variavel model
		model.addObject("status", status.value());

		switch (status.value()) {
			case 404:
				model.addObject("error", "Página não encontrada");
				model.addObject("message", "A url para a página '" + map.get("path") + "' não existe.");
				break;
			case 500:
				model.addObject("error", "Ocorreu um erro interno no servidor.");
				model.addObject("message", "Ocorreu um erro inesperado, tente mais tarde.");
				break;
			default:
				model.addObject("error", map.get("error"));
				model.addObject("message", map.get("message"));
				break;
		}

		return model;
	}

}
