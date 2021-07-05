package com.emerson.curso_spring_boot_MVC_com_thymeleaf.angular.exceptions.resources;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrorPersonalized extends StandardErrorPersonalized {
	private static final long serialVersionUID = 1L;

	private List<FieldMessagePersonalized> errors = new ArrayList<>();

	public ValidationErrorPersonalized(Long timestamp, Integer status, String error, String message, String path) {
		super(timestamp, status, error, message, path);
	}

	public List<FieldMessagePersonalized> getErrors() {
		return errors;
	}

	public void addError(String fieldName, String message) {
		errors.add(new FieldMessagePersonalized(fieldName, message));
	}

}
