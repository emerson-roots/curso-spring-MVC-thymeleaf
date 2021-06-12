package com.emerson.curso_spring_boot_MVC_com_thymeleaf.web.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	public String cadastrar(Departamento departamentoo) {
		return "departamento/cadastro";
	}

	@GetMapping("/listar")
	public String listar(ModelMap model) {

		List<Departamento> lista = service.buscarTodos();

		// adiciona uma lista de objetos a váriavel definida no frontend HTML
		// departamentosVariavelController é exatamente a váriavel
		// presenta no documento HTML de listar os departamentos
		model.addAttribute("departamentosVariavelController", lista);

		return "departamento/lista";
	}

	// aula 36
	//na aula 57 foi abordado a anotação @Valid e o parametro BindingResult
	//utilizados para validar campos
	@PostMapping("/salvar")
	public String salvar(@Valid Departamento departamento, BindingResult result, RedirectAttributes attr) {
		
		// implementado na aula 57
		// caso encontre erros na validação dos campos
		if (result.hasErrors()) {
			return "departamento/cadastro";
		}
		
		service.salvar(departamento);
		
		// na aula 41 - 7:01 - foi aprendido o por que usamos RedirectAttributes
		// como parametro aqui para enviar atributos ao frontend inves do modelmap.
		// o simples fato de usar um redirect para redirecionar a página
		attr.addFlashAttribute("variavelSuccessDoControllerParaFrontend", "Departamento inserido com sucesso!");
		return "redirect:/departamentos/cadastrar";
	}

	// aula 38
	// carrega a pagina com os dados do item selecionado para edicao
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		Departamento objDepto = service.buscarPorId(id);
		model.addAttribute("departamento", objDepto);
		return "departamento/cadastro";
	}

	// aula 38
	@PostMapping("/editar")
	//na aula 57 foi abordado a anotação @Valid e o parametro BindingResult
	//utilizados para validar campos
	public String editar(@Valid Departamento departamento, BindingResult result,RedirectAttributes attr) {
		
		// implementado na aula 57
		// caso encontre erros na validação dos campos
		if (result.hasErrors()) {
			return "departamento/cadastro";
		}
		
		service.editar(departamento);
		
		// na aula 41 - 7:01 - foi aprendido o por que usamos RedirectAttributes
		// como parametro aqui para enviar atributos ao frontend inves do modelmap.
		// o simples fato de usar um redirect para redirecionar a página
		attr.addFlashAttribute("variavelSuccessDoControllerParaFrontend", "Departamento editado com sucesso!");
		return "redirect:/departamentos/cadastrar";
	}

	// aula 39
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {

		// somente exclui departamentos se não possuir nenhum cargo relacionado
		// caso contrario envia uma variavel
		if (service.departamentoTemCargos(id)) {
			model.addAttribute("variavelFailDoControllerParaFrontend",
					"Departamento não removido pois possui cargos vinculados.");
		} else {
			service.excluir(id);
			model.addAttribute("variavelSucessDoControllerParaFrontend", "Departamento excluido com sucesso.");
		}

		// redirect: em aulas anteriores foi utilizado o redirect
		// porém nesta aula o professor abordou uma outra forma
		// de redirecionar para outra pagina do projeto
		// chamando apenas o metodo
		return listar(model);
	}
	
	/**
	 * ===============================================================================================
	 * 
	 * 							M I G R A Ç Ã O     A N G U L A R
	 * 
	 * metodos criados para adaptação do front-end para trabalhar com Angular
	 * 
	 * 
	 * ===============================================================================================
	 */
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Departamento departamento) {

		service.salvar(departamento);
		// URI do java.net
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(departamento.getId()).toUri();

		return ResponseEntity.created(uri).build();

	}
}
