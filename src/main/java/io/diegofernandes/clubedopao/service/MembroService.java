package io.diegofernandes.clubedopao.service;

import io.diegofernandes.clubedopao.model.ListaEncarregadoPao;

import java.util.Date;

public interface MembroService {

	ListaEncarregadoPao gerarListaEncarregadosPao(Date data);

}
