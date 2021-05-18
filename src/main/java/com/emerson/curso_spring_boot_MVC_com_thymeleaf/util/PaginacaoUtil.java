package com.emerson.curso_spring_boot_MVC_com_thymeleaf.util;

import java.util.List;

//aula 80
public class PaginacaoUtil<T> {

	// armazena a quantidade de linhas nas paginas
	private int tamanho;

	// armazena o numero da página atual selecionada
	private int pagina;

	// armazena o total de paginas no sistema de paginacao
	private long totalDePaginas;

	// recebe o resultado da consulta no banco de dados
	private List<T> registros;

	public PaginacaoUtil(int tamanho, int pagina, long totalDePaginas, List<T> registros) {
		super();
		this.tamanho = tamanho;
		this.pagina = pagina;
		this.totalDePaginas = totalDePaginas;
		this.registros = registros;
	}

	// não é necessário os metodos setters pois ja possui o constructor
	public int getTamanho() {
		return tamanho;
	}

	public int getPagina() {
		return pagina;
	}

	public long getTotalDePaginas() {
		return totalDePaginas;
	}

	public List<T> getRegistros() {
		return registros;
	}

}
