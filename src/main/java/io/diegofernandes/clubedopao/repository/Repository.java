package io.diegofernandes.clubedopao.repository;

import java.io.Serializable;
import java.util.List;

public interface Repository<T, K extends Serializable> extends Serializable {

	T find(K id);

	List<T> findAll(Integer firstResult, Integer maxResults);

	List<T> findAll();

	T save(T entity);

	void remove(T entity);

}
