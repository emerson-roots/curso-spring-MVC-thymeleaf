package com.emerson.curso_spring_boot_MVC_com_thymeleaf.angular.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.emerson.curso_spring_boot_MVC_com_thymeleaf.angular.validation.FuncionarioInsertOrUpdate;
import com.emerson.curso_spring_boot_MVC_com_thymeleaf.domain.Cargo;
import com.emerson.curso_spring_boot_MVC_com_thymeleaf.domain.Endereco;

@FuncionarioInsertOrUpdate
public class FuncionarioDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	@NotBlank(message = "Preenchimento obrigatório")
	private String nome;

	@NotNull(message = "Preenchimento obrigatório")
	@DecimalMin(value = "1", inclusive = false, message = "O salário deve ser MAIOR que {value}")
	@DecimalMax(value = "99999", inclusive = false, message = "O salário deve MENOR que {value}")
	private BigDecimal salario;

	@NotNull(message = "Preenchimento obrigatório")
	private LocalDate dataEntrada;

	private LocalDate dataSaida;

	@NotNull(message = "Preenchimento obrigatório")
	private Endereco endereco;

	@NotNull(message = "{NotNull.funcionario.cargo}")
	private Cargo cargo;

	public FuncionarioDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
