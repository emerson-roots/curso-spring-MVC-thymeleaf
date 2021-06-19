package com.emerson.curso_spring_boot_MVC_com_thymeleaf.exceptions.services;


public class DataIntegrityExceptionPersonalized extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public DataIntegrityExceptionPersonalized(String pMensagemExcessao) {
		super(pMensagemExcessao);
	}
	
	public DataIntegrityExceptionPersonalized(String pMensagem, Throwable pCause) {
		super(pMensagem, pCause);
	}

}
