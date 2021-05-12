package com.emerson.curso_spring_boot_MVC_com_thymeleaf.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.emerson.curso_spring_boot_MVC_com_thymeleaf.domain.Cargo;
import com.emerson.curso_spring_boot_MVC_com_thymeleaf.domain.Funcionario;
import com.emerson.curso_spring_boot_MVC_com_thymeleaf.domain.enums.UF;
import com.emerson.curso_spring_boot_MVC_com_thymeleaf.service.CargoService;
import com.emerson.curso_spring_boot_MVC_com_thymeleaf.service.FuncionarioService;

//aula 12
@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {

	// aula 49
	@Autowired
	private FuncionarioService funcionarioService;
	@Autowired
	private CargoService cargoService;

	@GetMapping("/cadastrar")
	public String cadastrar(Funcionario funcionario) {
		return "/funcionario/cadastro";
	}

	@GetMapping("/listar")
	public String listar(ModelMap model) {

		// aula 51
		List<Funcionario> lista = funcionarioService.buscarTodos();
		model.addAttribute("funcionariosVariavelController", lista);
		return "/funcionario/lista";
	}

	// aula 49
	@PostMapping("/salvar")
	public String salvar(Funcionario funcionario, RedirectAttributes attr) {
		funcionarioService.salvar(funcionario);
		attr.addFlashAttribute("variavelSuccessDoControllerParaFrontend", "Funcionario inserido com sucesso.");
		return "redirect:/funcionarios/cadastrar";
	}

	// aula 49 - alimenta comcoboxes
	@ModelAttribute("variavelCargosDoControllerParaFrontend")
	public List<Cargo> getCargos() {
		return cargoService.buscarTodos();
	}

	// aula 49 - alimenta comboboxes
	@ModelAttribute("variavelUFsDoControllerParaFrontend")
	public UF[] getUFs() {
		return UF.values();
	}

	// aula 52
	// carrega a pagina com os dados do item selecionado para edicao
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		Funcionario obj = funcionarioService.buscarPorId(id);
		model.addAttribute("funcionario", obj);
		return "funcionario/cadastro";
	}

	// aula 52
	@PostMapping("/editar")
	public String editar(Funcionario funcionario, RedirectAttributes attr) {
		funcionarioService.editar(funcionario);
		attr.addFlashAttribute("variavelSuccessDoControllerParaFrontend", "Funcionario editado com sucesso!");
		return "redirect:/funcionarios/cadastrar";
	}

	// aula 52
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
		funcionarioService.excluir(id);
		attr.addFlashAttribute("variavelSuccessDoControllerParaFrontend", "Funcionario excluido com sucesso.");
		return "redirect:/funcionarios/listar";
	}

}
