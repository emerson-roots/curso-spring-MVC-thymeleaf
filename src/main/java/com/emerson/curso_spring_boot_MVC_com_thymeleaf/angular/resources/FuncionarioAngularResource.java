package com.emerson.curso_spring_boot_MVC_com_thymeleaf.angular.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.emerson.curso_spring_boot_MVC_com_thymeleaf.angular.dto.FuncionarioNewDTO;
import com.emerson.curso_spring_boot_MVC_com_thymeleaf.angular.services.FuncionarioAngularService;
import com.emerson.curso_spring_boot_MVC_com_thymeleaf.domain.Funcionario;
import com.emerson.curso_spring_boot_MVC_com_thymeleaf.domain.enums.UF;

@RestController
@RequestMapping(value = "/employee")
public class FuncionarioAngularResource {

	@Autowired
	private FuncionarioAngularService service;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody FuncionarioNewDTO funcionario) {

		Funcionario newObj = service.fromDTO(funcionario);
		
		newObj = service.insert(newObj);
		// URI do java.net
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId())
				.toUri();

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

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Funcionario> findById(@PathVariable Long id) {
		Funcionario obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody Funcionario obj, @PathVariable Long id) {
		service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/ufs", method = RequestMethod.GET)
	public ResponseEntity<UF[]> getUFs() {
		return ResponseEntity.ok().body(UF.values());
	}

}
