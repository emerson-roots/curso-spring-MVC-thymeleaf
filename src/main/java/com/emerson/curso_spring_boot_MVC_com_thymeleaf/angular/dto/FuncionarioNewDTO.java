package com.emerson.curso_spring_boot_MVC_com_thymeleaf.angular.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.emerson.curso_spring_boot_MVC_com_thymeleaf.angular.validation.FuncionarioInsert;
import com.emerson.curso_spring_boot_MVC_com_thymeleaf.domain.Cargo;
import com.emerson.curso_spring_boot_MVC_com_thymeleaf.domain.Endereco;

@FuncionarioInsert
public class FuncionarioNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "Preenchimento obrigatório")
	@Size(max = 255, min = 3)
	private String nome;
	
	@NotNull(message = "Preenchimento obrigatório")
	private BigDecimal salario;

	@NotNull(message = "Preenchimento obrigatório")
	private LocalDate dataEntrada;
	
	private LocalDate dataSaida;
	
	@NotNull(message = "Preenchimento obrigatório")
	private Endereco endereco;
	
	@NotNull(message = "{NotNull.funcionario.cargo}")
	private Cargo cargo;

	public FuncionarioNewDTO() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	public LocalDate getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(LocalDate dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public LocalDate getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(LocalDate dataSaida) {
		this.dataSaida = dataSaida;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

}
