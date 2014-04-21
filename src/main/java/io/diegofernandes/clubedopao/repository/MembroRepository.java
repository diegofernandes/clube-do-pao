package io.diegofernandes.clubedopao.repository;

import java.util.List;

import io.diegofernandes.clubedopao.model.Membro;

public interface MembroRepository extends Repository<Membro, Long> {

	Membro findByEmail(String email);

	List<Membro> findByNameOrEmail(String filter, Integer firstResult, Integer maxResults);

}
