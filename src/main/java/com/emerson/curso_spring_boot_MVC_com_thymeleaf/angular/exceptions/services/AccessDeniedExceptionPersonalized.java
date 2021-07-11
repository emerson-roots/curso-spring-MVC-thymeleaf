package com.emerson.curso_spring_boot_MVC_com_thymeleaf.angular.exceptions.services;


public class AccessDeniedExceptionPersonalized extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public AccessDeniedExceptionPersonalized(String pMensagemExcessao) {
		super(pMensagemExcessao);
	}
	
	public AccessDeniedExceptionPersonalized(String pMensagem, Throwable pCause) {
		super(pMensagem, pCause);
	}

}
