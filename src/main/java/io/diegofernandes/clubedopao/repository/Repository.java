package io.diegofernandes.clubedopao.repository;

import java.io.Serializable;
import java.util.List;

public interface Repository<T, K extends Serializable> extends Serializable {

	public T find(K id);

	public List<T> findAll(Integer firstResult, Integer maxResults);

	public List<T> findAll();

	public T save(T entity);

	public void remove(T entity);

}
