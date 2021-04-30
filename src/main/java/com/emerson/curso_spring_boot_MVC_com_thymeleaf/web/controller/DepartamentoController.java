package com.emerson.curso_spring_boot_MVC_com_thymeleaf.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//aula 12
@Controller
@RequestMapping("/departamentos")
public class DepartamentoController {
	
	//endpoint para pagina de cadastro de departamentos
	@GetMapping("/cadastrar")
	public String cadastrar() {
		return "/departamento/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar() {
		return "/departamento/lista";
	}
}
