package io.diegofernandes.clubedopao;

import io.diegofernandes.clubedopao.model.EncarregadoPao;
import io.diegofernandes.clubedopao.service.MembroService;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class MembroServiceImplTest {

	@Autowired
	private MembroService membroService;

	@Before
	private void setUp() {
	}

	@Test
	public void test_lista_encarregados() {
		final Date data = new Date();
		final List<EncarregadoPao> encarregadosPao = this.membroService
				.gerarListaEncarregadosPao(data);

	}

}
