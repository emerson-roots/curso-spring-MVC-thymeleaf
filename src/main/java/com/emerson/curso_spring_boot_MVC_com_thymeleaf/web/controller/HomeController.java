package com.emerson.curso_spring_boot_MVC_com_thymeleaf.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//aula 10 - 6:00
@Controller
public class HomeController {

	@GetMapping("/")
	public String home() {
		return "/home";
	}
}
