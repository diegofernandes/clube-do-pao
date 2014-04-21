package io.diegofernandes.clubedopao.repository.impl;

import java.util.List;

import io.diegofernandes.clubedopao.model.Membro;
import io.diegofernandes.clubedopao.repository.MembroRepository;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

@Repository
public class MembroRepositoryImpl extends AbstractRepository<Membro, Long>
		implements MembroRepository {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8105069030098279004L;

	@Override
	public Membro findByEmail(final String email) {
		final TypedQuery<Membro> query = this.getEntityManager()
				.createNamedQuery("Membro.findByEmail", Membro.class);

		query.setParameter("email", email);
		return query.getSingleResult();
	}

	@Override
	public List<Membro> findByNameOrEmail(final String filter,
			final Integer firstResult, final Integer maxResults) {
		final TypedQuery<Membro> typedQuery = this.getEntityManager()
				.createNamedQuery("Membro.findByNomeOrEmail", Membro.class);

		addPagination(firstResult, maxResults, typedQuery);

		typedQuery.setParameter("filter", "%"+  filter + "%");

		return typedQuery.getResultList();
	}

}
