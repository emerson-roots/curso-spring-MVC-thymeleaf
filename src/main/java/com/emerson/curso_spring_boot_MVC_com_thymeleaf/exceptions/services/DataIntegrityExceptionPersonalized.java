package com.emerson.curso_spring_boot_MVC_com_thymeleaf.exceptions.services;


//aula 36 - implementação padrao de excessão personalizada
public class DataIntegrityExceptionPersonalized extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	// recebe uma string com a mensgem de excessao e repassa a mensagem pra quem a chama
	public DataIntegrityExceptionPersonalized(String pMensagemExcessao) {
		super(pMensagemExcessao);
	}
	
	
	// sobrecarga - recebe a mensagem e uma outra excessão pCause de alguma coisa q aconteceu antes
	public DataIntegrityExceptionPersonalized(String pMensagem, Throwable pCause) {
		super(pMensagem, pCause);
	}

}
