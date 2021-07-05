package com.emerson.curso_spring_boot_MVC_com_thymeleaf.angular.exceptions.resources;

import java.io.Serializable;

public class FieldMessagePersonalized implements Serializable {
	private static final long serialVersionUID = 1L;

	private String fieldName;
	private String message;

	public FieldMessagePersonalized() {

	}

	public FieldMessagePersonalized(String fieldName, String message) {
		super();
		this.fieldName = fieldName;
		this.message = message;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
