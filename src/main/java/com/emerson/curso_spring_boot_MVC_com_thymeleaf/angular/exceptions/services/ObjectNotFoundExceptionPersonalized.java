package com.emerson.curso_spring_boot_MVC_com_thymeleaf.angular.exceptions.services;


public class ObjectNotFoundExceptionPersonalized extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ObjectNotFoundExceptionPersonalized(String pMensagemExcessao) {
		super(pMensagemExcessao);
	}
	
	public ObjectNotFoundExceptionPersonalized(String pMensagem, Throwable pCause) {
		super(pMensagem, pCause);
	}

}
