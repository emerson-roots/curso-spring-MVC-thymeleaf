package com.emerson.curso_spring_boot_MVC_com_thymeleaf.web.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.emerson.curso_spring_boot_MVC_com_thymeleaf.domain.Cargo;
import com.emerson.curso_spring_boot_MVC_com_thymeleaf.domain.Funcionario;
import com.emerson.curso_spring_boot_MVC_com_thymeleaf.domain.enums.UF;
import com.emerson.curso_spring_boot_MVC_com_thymeleaf.service.CargoService;
import com.emerson.curso_spring_boot_MVC_com_thymeleaf.service.FuncionarioService;
import com.emerson.curso_spring_boot_MVC_com_thymeleaf.web.validator.FuncionarioValidator;

//aula 12
@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {

	// aula 49
	@Autowired
	private FuncionarioService funcionarioService;
	@Autowired
	private CargoService cargoService;
	
	//aula 60 - criado para validar a regra de negocio referente[
	//as datas e entrada e saida
	//onde a data de saida nao pode ser MENOR que a data de entrada
	//tem vinculo com a classe FuncionarioValidator
	//
	//a anotação @InitBinder diz para a aplicação que este metodo 
	//sera o primeiro metodo da classe que vai ser executado
	//e ao ser executado, o Spring MVC vai até a classe FuncionarioValidator
	//fazer a validação, antes de liberar a requisição ao metodo salvar ou editar
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new FuncionarioValidator());
	}
	
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
	public String salvar(@Valid Funcionario funcionario, BindingResult result, RedirectAttributes attr) {
		
		if(result.hasErrors()) {
			return "/funcionario/cadastro";
		}
		
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
	public String editar(@Valid Funcionario funcionario, BindingResult result, RedirectAttributes attr) {
		
		if(result.hasErrors()) {
			return "/funcionario/cadastro";
		}
		
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

	/**
	 * aula 53 DAR ATENÇÃO ESPECIAL AO NOME DA VARIAVEL DO METODO ADDATTRIBUTE
	 */
	@GetMapping("/buscar/nome")
	public String getPorNome(@RequestParam("nome") String nome, ModelMap model) {
		List<Funcionario> obj = funcionarioService.buscarPorNome(nome);

		// ATENÇÃO AO NOME DA VARIÁVEL DO METODO ADDATRIBUTE.
		//
		// o metodo "listar" aqui da classe utiliza uma variavel para acessar a lista de
		// funcionarios
		// qualquer outro metodo que seja necessário enviar parametros para a view
		// deve UTILIZAR O MESMO NOME DE VARIAVEL da pagina que acessa a lista
		// em testes, tentei colocar uma váriavel diferente
		// e a consulta por nome não estava retornando registros
		model.addAttribute("funcionariosVariavelController", obj);
		return "/funcionario/lista";
	}

	// aula 54
	@GetMapping("/buscar/cargo")
	public String getPorCargo(@RequestParam("id") Long id, ModelMap model) {
		List<Funcionario> obj = funcionarioService.buscarPorCargo(id);
		model.addAttribute("funcionariosVariavelController", obj);
		return "/funcionario/lista";
	}

	// aula 55
	// a anotação @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	// serve para convertar a data la no front-end para o tipo LocalDate
	@GetMapping("/buscar/data")
	public String getPorDatas(@RequestParam(name="entrada", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate entrada, 
							  @RequestParam(name="saida", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate saida,
							  ModelMap model) {
		List<Funcionario> obj = funcionarioService.buscarPorDatas(entrada, saida);
		model.addAttribute("funcionariosVariavelController", obj);
		return "/funcionario/lista";
	}
}
