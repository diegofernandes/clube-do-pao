package io.diegofernandes.clubedopao.service;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import io.diegofernandes.clubedopao.BaseTest;
import io.diegofernandes.clubedopao.model.EncarregadoPao;
import io.diegofernandes.clubedopao.model.Membro;
import io.diegofernandes.clubedopao.repository.MembroRepository;
import io.diegofernandes.clubedopao.service.MembroService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.google.common.base.Function;
import com.google.common.collect.Maps;


public class MembroServiceImplTest extends BaseTest{

	@Autowired
	private MembroService membroService;
	
	@Autowired
	private MembroRepository membroRepository;

	private Map<Long,Membro> mapMembros;

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
		final List<EncarregadoPao> encarregadosPao = this.membroService
				.gerarListaEncarregadosPao(calendar.getTime());
		
		for (EncarregadoPao encarregadoPao : encarregadosPao) {
			Calendar data = Calendar.getInstance();
		    data.setTime(encarregadoPao.getData());
		    
		    int dayOfWeek =  data.get(Calendar.DAY_OF_WEEK);
		    
		    assertNotEquals(dayOfWeek, Calendar.SATURDAY);
		    assertNotEquals(dayOfWeek, Calendar.SUNDAY);
		    
		    if(encarregadoPao.getIdEncarregado() !=null){
		    		final List<Integer> disponibilidade = mapMembros.get(encarregadoPao.getIdEncarregado()).getDisponbilidade();
		    
		    		assertThat(disponibilidade,hasItem(dayOfWeek));
		    }
		    
		}

	}

}
