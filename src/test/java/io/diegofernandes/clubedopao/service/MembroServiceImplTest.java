package io.diegofernandes.clubedopao.service;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;
import io.diegofernandes.clubedopao.BaseTest;
import io.diegofernandes.clubedopao.model.DiaPao;
import io.diegofernandes.clubedopao.model.ListaEncarregadoPao;
import io.diegofernandes.clubedopao.model.Membro;
import io.diegofernandes.clubedopao.repository.MembroRepository;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.google.common.base.Function;
import com.google.common.collect.Maps;

public class MembroServiceImplTest extends BaseTest {

	@Autowired
	private MembroService membroService;

	@Autowired
	private MembroRepository membroRepository;

	private Map<Long, Membro> mapMembros;

	@Before
	public void setUp() {

		final List<Membro> membros = membroRepository.findAll();
		mapMembros = Maps.uniqueIndex(membros, new Function<Membro, Long>() {

			@Override
			public Long apply(Membro input) {
				return input.getId();
			}
		});
	}

	@Test
	@DatabaseSetup("/dataset/membrosTodosDiasDisponiveis.xml")
	public void test_lista_encarregados() {
		final Calendar calendar = Calendar.getInstance();
		final ListaEncarregadoPao encarregadosPao = this.membroService
				.gerarListaEncarregadosPao(calendar.getTime());

		for (DiaPao diaoPao : encarregadosPao.getListDiaPao()) {
			Calendar data = Calendar.getInstance();
			data.setTime(diaoPao.getData());

			int dayOfWeek = data.get(Calendar.DAY_OF_WEEK);

			if (diaoPao.getIdEncarregado() != null) {
				assertNotEquals(dayOfWeek, Calendar.SATURDAY);
				assertNotEquals(dayOfWeek, Calendar.SUNDAY);
				final List<Integer> disponibilidade = mapMembros.get(
						diaoPao.getIdEncarregado()).getDisponibilidade();

				assertThat(disponibilidade, hasItem(dayOfWeek));
			}

		}

	}

}
