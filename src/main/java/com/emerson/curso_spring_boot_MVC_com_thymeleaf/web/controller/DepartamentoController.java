package com.emerson.curso_spring_boot_MVC_com_thymeleaf.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.emerson.curso_spring_boot_MVC_com_thymeleaf.domain.Departamento;
import com.emerson.curso_spring_boot_MVC_com_thymeleaf.service.DepartamentoService;

//aula 12
@Controller
@RequestMapping("/departamentos")
public class DepartamentoController {

	// aula 36
	@Autowired
	private DepartamentoService service;

	// endpoint para pagina de cadastro de departamentos
	@GetMapping("/cadastrar")
	public String cadastrar(Departamento departamento) {
		return "/departamento/cadastro";
	}

	@GetMapping("/listar")
	public String listar(ModelMap model) {

		List<Departamento> lista = service.buscarTodos();

		// adiciona uma lista de objetos a váriavel definida no frontend HTML
		// departamentosVariavelController é exatamente a váriavel
		// presenta no documento HTML de listar os departamentos
		model.addAttribute("departamentosVariavelController", lista);

		return "/departamento/lista";
	}

	// aula 36
	@PostMapping("/salvar")
	public String salvar(Departamento departamento) {
		service.salvar(departamento);
		return "redirect:/departamentos/cadastrar";
	}
}
