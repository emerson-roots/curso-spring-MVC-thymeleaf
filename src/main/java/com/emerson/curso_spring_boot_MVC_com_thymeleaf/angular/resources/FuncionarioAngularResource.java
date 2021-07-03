package com.emerson.curso_spring_boot_MVC_com_thymeleaf.angular.resources;

import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.emerson.curso_spring_boot_MVC_com_thymeleaf.angular.dto.FuncionarioDTO;
import com.emerson.curso_spring_boot_MVC_com_thymeleaf.angular.services.FuncionarioAngularService;
import com.emerson.curso_spring_boot_MVC_com_thymeleaf.domain.Funcionario;
import com.emerson.curso_spring_boot_MVC_com_thymeleaf.domain.enums.UF;

@RestController
@RequestMapping(value = "/employee")
public class FuncionarioAngularResource {

	@Autowired
	private FuncionarioAngularService service;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody FuncionarioDTO funcionarioDTO) {

		Funcionario newObj = service.fromDTO(funcionarioDTO);

		newObj = service.insert(newObj);
		// URI do java.net
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();

		return ResponseEntity.created(uri).build();

	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Funcionario>> findAll() {
		return ResponseEntity.ok().body(service.findAll());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/search/name", method = RequestMethod.GET)
	public ResponseEntity<List<Funcionario>> findAllByName(
			@RequestParam(value = "nome", defaultValue = "") String nome) {

		List<Funcionario> list = service.findAllByName(nome);
		return ResponseEntity.ok().body(list);

	}

	@RequestMapping(value = "/search/office", method = RequestMethod.GET)
	public ResponseEntity<List<Funcionario>> findAllByCargo(@RequestParam(value = "id", defaultValue = "") Long id) {
		List<Funcionario> list = service.findAllByCargo(id);
		return ResponseEntity.ok().body(list);
	}

	@RequestMapping(value = "/search/date", method = RequestMethod.GET)
	public ResponseEntity<List<Funcionario>> findAllByDataEntradaDataSaida(
			@RequestParam(name = "dataEntrada", required = false, defaultValue = "") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataEntrada,
			@RequestParam(name = "dataSaida", required = false, defaultValue = "") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataSaida) {

		List<Funcionario> list = new ArrayList<>();
		
		if (dataEntrada != null && dataSaida != null) {
			list = service.findAllByDataEntradaAndDataSaida(dataEntrada, dataSaida);
		} else if (dataEntrada != null) {
			list = service.findAllByDataEntrada(dataEntrada);
		} else if (dataSaida != null) {
			list = service.findAllByDataSaida(dataSaida);
		}

		return ResponseEntity.ok().body(list);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Funcionario> findById(@PathVariable Long id) {
		Funcionario obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody FuncionarioDTO funcionarioDTO, @PathVariable Long id) {
		Funcionario funcUpdate = service.fromDTO(funcionarioDTO);
		service.update(funcUpdate);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/ufs", method = RequestMethod.GET)
	public ResponseEntity<UF[]> getUFs() {
		return ResponseEntity.ok().body(UF.values());
	}

}
