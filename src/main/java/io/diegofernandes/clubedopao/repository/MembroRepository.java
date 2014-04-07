package io.diegofernandes.clubedopao.repository;

import io.diegofernandes.clubedopao.model.Membro;

public interface MembroRepository extends Repository<Membro, Long> {

	Membro findByEmail(String email);

}
