package io.diegofernandes.clubedopao.repository.impl;

import io.diegofernandes.clubedopao.repository.Repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly=true)
public abstract class AbstractRepository<T, K extends Serializable> implements
		Repository<T, K> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8518632801725629467L;

	private final Class<T> clazz;

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public AbstractRepository() {
		this.clazz = ((Class) ((java.lang.reflect.ParameterizedType) this
				.getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
	}

	@Override
	public T find(final K id) {
		return this.entityManager.find(this.clazz, id);
	}

	@Override
	public List<T> findAll() {
		return this.findAll(null, null);
	}

	@Override
	public List<T> findAll(final Integer firstResult, final Integer maxResults) {

		final CriteriaQuery<T> criteria = this.entityManager
				.getCriteriaBuilder().createQuery(this.clazz);
		criteria.select(criteria.from(this.clazz));

		final TypedQuery<T> query = this.entityManager.createQuery(criteria);

		addPagination(firstResult, maxResults, query);
		return query.getResultList();
	}

	protected void addPagination(final Integer firstResult,
			final Integer maxResults, final TypedQuery<T> query) {
		if (maxResults != null) {
			query.setMaxResults(maxResults);
		}
		if (firstResult != null) {
			query.setFirstResult(firstResult);
		}
	}

	@Override
	@Transactional(readOnly=false)
	public T save(final T entity) {
		return this.entityManager.merge(entity);
	}

	@Override
	@Transactional(readOnly=false)
	public void remove(final T entity) {
		this.entityManager.remove(entity);
	}
	@Override
	@Transactional(readOnly=false)
	public void remove(final K id) {
		this.remove(this.find(id));
	}

	public EntityManager getEntityManager() {
		return this.entityManager;
	}

}
