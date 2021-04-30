package com.emerson.curso_spring_boot_MVC_com_thymeleaf.domain;

import java.io.Serializable;

import javax.persistence.*;

//aula 15
//esta classe utiliza um dos pilares de POO que é a HERANÇA
//tem como função eliminar a necessidade de declarar/setar ids nas entidades
//para seu uso, ao implementar a entidade é necessário colocar a instrução "extends" herdando assim a classe abstrata
//passando como parametro um tipo LONG
@SuppressWarnings("serial")
@MappedSuperclass
public abstract class AbstractEntity<ID extends Serializable> implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private ID id;

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractEntity<?> other = (AbstractEntity<?>) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "[id=" + id + "]";
	}

}
