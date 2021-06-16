package com.emerson.curso_spring_boot_MVC_com_thymeleaf.exceptions.resources;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice 
public class ResourceExceptionHandlerPersonalized {

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<StandardErrorPersonalized> validationPersonalized(ConstraintViolationException e,
			HttpServletRequest pRequest) {

		ValidationErrorPersonalized err = new ValidationErrorPersonalized(System.currentTimeMillis(),
				HttpStatus.INTERNAL_SERVER_ERROR.value(), "Erro de validação de campos.", e.getConstraintViolations().toString(), pRequest.getRequestURI());

		for (ConstraintViolation<?> x : e.getConstraintViolations() ) {
			err.addError(x.getPropertyPath().toString(), x.getMessage());
		}
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
	}

}
