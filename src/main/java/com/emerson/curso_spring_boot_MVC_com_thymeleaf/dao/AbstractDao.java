package com.emerson.curso_spring_boot_MVC_com_thymeleaf.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * aula 21
 * 
 * DAO Generico 2 tipos genericos na assinatura da classe;
 * 
 * @param T:  representa a entidade. Ex. um Cargo, um Departamento, Um
 *            funcionario
 * @param PK: representa o tipo de chave primaria que esta sendo usada. Ex. pk
 *            long, pk integer ou qualquer outro tipo.
 */
public abstract class AbstractDao<T, PK extends Serializable> {

	@SuppressWarnings("unchecked")
	private final Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
			.getActualTypeArguments()[0];

	@PersistenceContext
	private EntityManager entityManager;

	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public void save(T entity) {

		entityManager.persist(entity);
	}

	public void update(T entity) {

		entityManager.merge(entity);
	}

	public void delete(PK id) {

		entityManager.remove(entityManager.getReference(entityClass, id));
	}

	public T findById(PK id) {

		return entityManager.find(entityClass, id);
	}

	public List<T> findAll() {

		return entityManager.createQuery("from " + entityClass.getSimpleName(), entityClass).getResultList();
	}

	// usado para fazer consultas via JPQL
	protected List<T> createQuery(String jpql, Object... params) {
		TypedQuery<T> query = entityManager.createQuery(jpql, entityClass);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i + 1, params[i]);
		}
		return query.getResultList();
	}
}
