package com.emerson.curso_spring_boot_MVC_com_thymeleaf.angular.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.emerson.curso_spring_boot_MVC_com_thymeleaf.angular.services.CargoAngularService;
import com.emerson.curso_spring_boot_MVC_com_thymeleaf.domain.Cargo;

@RestController
@RequestMapping(value = "/office")
public class CargoAngularResource {

	@Autowired
	private CargoAngularService service;
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Cargo cargo) {

		service.insert(cargo);
		// URI do java.net
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cargo.getId())
				.toUri();

		return ResponseEntity.created(uri).build();

	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Cargo>> findAll() {
		return ResponseEntity.ok().body(service.findAll());
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Cargo> findById(@PathVariable Long id) {
		Cargo obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody Cargo obj, @PathVariable Long id) {
		service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<Cargo>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer pPage,
			@RequestParam(value = "linesPerPage", defaultValue = "5") Integer pLinesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String pOrderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String pDirectionOrdenation) {

		Page<Cargo> list = service.findPage(pPage, pLinesPerPage, pOrderBy, pDirectionOrdenation);
		return ResponseEntity.ok().body(list);

	}

}
