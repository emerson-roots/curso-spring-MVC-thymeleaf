package com.emerson.curso_spring_boot_MVC_com_thymeleaf.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.emerson.curso_spring_boot_MVC_com_thymeleaf.domain.Cargo;
import com.emerson.curso_spring_boot_MVC_com_thymeleaf.domain.Departamento;
import com.emerson.curso_spring_boot_MVC_com_thymeleaf.service.CargoService;
import com.emerson.curso_spring_boot_MVC_com_thymeleaf.service.DepartamentoService;

//aula 12
@Controller
@RequestMapping("/cargos")
public class CargoController {

	@Autowired
	private CargoService cargoService;

	@Autowired
	private DepartamentoService departamentoService;

	@GetMapping("/cadastrar")
	public String cadastrar(Cargo cargo) {
		return "/cargo/cadastro";
	}

	@GetMapping("/listar")
	public String listar(ModelMap model) {
		
		List<Cargo> lista = cargoService.buscarTodos();

		// adiciona uma lista de objetos a váriavel definida no frontend HTML
		// cargosVariavelController é exatamente a váriavel
		// presenta no documento HTML de listar os cargos
		model.addAttribute("cargosVariavelController", lista);
		return "/cargo/lista";
	}

	// aula 43
	//na aula 57 foi abordado a anotação @Valid e o parametro BindingResult
	//utilizados para validar campos
	@PostMapping("/salvar")
	public String salvar(@Valid Cargo cargo, BindingResult result, RedirectAttributes attr) {
		
		// implementado na aula 57
		// caso encontre erros na validação dos campos
		if (result.hasErrors()) {
			return "/cargo/cadastro";
		}
		
		cargoService.salvar(cargo);
		attr.addFlashAttribute("variavelSuccessDoControllerParaFrontend", "Cargo inserido com sucesso.");
		return "redirect:/cargos/cadastrar";
	}

	// aula 43
	/* LER SOBRE MODEL ATTRIBUTE, ELA É MUITO IMPORTANTE **/
	@ModelAttribute("variavelDepartamentoDoControllerParaFrontend")
	public List<Departamento> listaDeDepartamentos() {
		return departamentoService.buscarTodos();
	}

	// aula 46
	// carrega a pagina com os dados do item selecionado para edicao
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		Cargo objDepto = cargoService.buscarPorId(id);
		model.addAttribute("cargo", objDepto);
		return "/cargo/cadastro";
	}

	//aula 46
	//na aula 57 foi abordado a anotação @Valid e o parametro BindingResult
	//utilizados para validar campos
	@PostMapping("/editar")
	public String editar(@Valid Cargo cargo, BindingResult result ,RedirectAttributes attr) {
		
		// implementado na aula 57
		// caso encontre erros na validação dos campos
		if (result.hasErrors()) {
			return "/cargo/cadastro";
		}
		cargoService.editar(cargo);
		attr.addFlashAttribute("variavelSuccessDoControllerParaFrontend", "Cargo editado com sucesso!");
		return "redirect:/cargos/cadastrar";
	}
	
	//aula 47
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
		if (cargoService.cargoTemFuncionarios(id)) {
			attr.addFlashAttribute("variavelFailDoControllerParaFrontend", "Cargo não excluido. Tem funcionário(s) vinculado(s).");
		} else {
			cargoService.excluir(id);
			attr.addFlashAttribute("variavelSuccessDoControllerParaFrontend", "Cargo excluido com sucesso.");
		}
		return "redirect:/cargos/listar";
	}
	
}
