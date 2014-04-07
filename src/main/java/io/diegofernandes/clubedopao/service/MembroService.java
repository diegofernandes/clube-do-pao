package io.diegofernandes.clubedopao.service;

import io.diegofernandes.clubedopao.model.EncarregadoPao;

import java.util.Date;
import java.util.List;

public interface MembroService {

	List<EncarregadoPao> gerarListaEncarregadosPao(Date data);

}
