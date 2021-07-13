package com.emerson.curso_spring_boot_MVC_com_thymeleaf.angular.exceptions.resources;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.emerson.curso_spring_boot_MVC_com_thymeleaf.angular.exceptions.services.DataIntegrityExceptionPersonalized;
import com.emerson.curso_spring_boot_MVC_com_thymeleaf.angular.exceptions.services.ObjectNotFoundExceptionPersonalized;

@ControllerAdvice
public class ResourceExceptionHandlerPersonalized {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardErrorPersonalized> validationPersonalized(MethodArgumentNotValidException e,
			HttpServletRequest pRequest) {

		ValidationErrorPersonalized err = new ValidationErrorPersonalized(System.currentTimeMillis(),
				HttpStatus.UNPROCESSABLE_ENTITY.value(), "Erro de validação de campos", e.getMessage(), pRequest.getRequestURI());

		// percorre cada erro adicionando ao metodo personalizado addError
		for (FieldError x : e.getBindingResult().getFieldErrors()) {
			err.addError(x.getField(), x.getDefaultMessage());
		}
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(err);
	}

	@ExceptionHandler(DataIntegrityExceptionPersonalized.class)
	public ResponseEntity<StandardErrorPersonalized> dataIntegrityPersonalized(DataIntegrityExceptionPersonalized e,
			HttpServletRequest pRequest) {
		StandardErrorPersonalized err = new StandardErrorPersonalized(System.currentTimeMillis(),
				HttpStatus.BAD_REQUEST.value(), "Integridade de dados", e.getMessage(), pRequest.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	@ExceptionHandler(ObjectNotFoundExceptionPersonalized.class)
	public ResponseEntity<StandardErrorPersonalized> objectNotFoundPersonalized(ObjectNotFoundExceptionPersonalized pNotFound,
			HttpServletRequest pRequest) {

		StandardErrorPersonalized err = new StandardErrorPersonalized(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(),
				"Objeto não encontrado", pNotFound.getMessage(), pRequest.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<StandardErrorPersonalized> accessDeniedPersonalized(AccessDeniedException e,
			HttpServletRequest pRequest) {

		StandardErrorPersonalized err = new StandardErrorPersonalized(System.currentTimeMillis(), HttpStatus.FORBIDDEN.value(),
				e.getMessage(), "Não autorizado: Seu perfil de usuário não tem permissão para acessar este recurso.", pRequest.getRequestURI());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
	}

}
