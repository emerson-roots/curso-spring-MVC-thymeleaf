package com.emerson.curso_spring_boot_MVC_com_thymeleaf.angular.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UsuarioAngularResource {

//	@Autowired
//	private UsuarioAngularService service;
//
//	@RequestMapping(method = RequestMethod.POST)
//	public ResponseEntity<Void> insert(@Valid @RequestBody UsuarioNewDTO objDTO) {
//		Usuario obj = service.fromDTO(objDTO);
//		obj = service.insert(obj);
//		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId())
//				.toUri();
//
//		return ResponseEntity.created(uri).build();
//	}
//
//	@RequestMapping(method = RequestMethod.GET)
//	public ResponseEntity<List<UsuarioDTO>> findAll() {
//		List<Usuario> list = service.findAll();
//		List<UsuarioDTO> listDto = list.stream().map(obj -> new UsuarioDTO(obj)).collect(Collectors.toList());
//		return ResponseEntity.ok().body(listDto);
//	}

}
