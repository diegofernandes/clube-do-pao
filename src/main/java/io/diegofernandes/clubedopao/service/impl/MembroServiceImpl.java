package io.diegofernandes.clubedopao.service.impl;

import io.diegofernandes.clubedopao.model.EncarregadoPao;
import io.diegofernandes.clubedopao.model.Membro;
import io.diegofernandes.clubedopao.repository.MembroRepository;
import io.diegofernandes.clubedopao.service.MembroService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import com.google.common.collect.Ordering;
import com.google.common.collect.SetMultimap;
import com.google.common.collect.Sets;
import com.google.common.collect.Sets.SetView;
import com.google.common.collect.TreeMultimap;
import com.google.common.primitives.Ints;

@Service
public class MembroServiceImpl implements MembroService {
	
	private final static int QTD_DIAS = 30;

	@Autowired
	private MembroRepository membroRepository;
	
	private final static Ordering<Membro> ORDERING = new Ordering<Membro>() {
		
		@Override
		public int compare(Membro left, Membro right) {
			return left.getDisponibilidade().size() - right.getDisponibilidade().size();
		}
	};

	@Override
	public List<EncarregadoPao> gerarListaEncarregadosPao(final Date dataInicio) {
		final List<Membro> membros = membroRepository.findAll();
		final int qtdMembros = membros.size();

		List<EncarregadoPao> listEncarregadoPao = new ArrayList<>();

		Calendar data = Calendar.getInstance();
		data.setTime(dataInicio);

		Calendar dataFim = (Calendar) data.clone();
		dataFim.add(Calendar.DAY_OF_MONTH, QTD_DIAS);

		SetMultimap<Integer, Membro> inverse = prepareMultimap(membros);
		
		SetMultimap<Integer, Membro> cicloMembros = HashMultimap.create();
		
		int dayOfWeek;
		int ciclo = 1;
		EncarregadoPao encarregadoPao;
		while (data.compareTo(dataFim) <= 0) {
			if (isWorkDay(data)) {
				dayOfWeek =  data.get(Calendar.DAY_OF_WEEK);
				encarregadoPao = new EncarregadoPao();
				listEncarregadoPao.add(encarregadoPao);
				encarregadoPao.setData(data.getTime());
				
				Set<Membro> membrosDia = inverse.get(dayOfWeek);
				Set<Membro> membrosCiclo = cicloMembros.get(ciclo);
				
				SetView<Membro> membrosDisponiveis = Sets.difference(membrosDia, membrosCiclo);
				
				if(!membrosDisponiveis.isEmpty()){
					Membro membro = ORDERING.min(membrosDisponiveis);
					cicloMembros.put(ciclo, membro);
					encarregadoPao.setIdEncarregado(membro.getId());
					encarregadoPao.setNomeEncarregado(membro.getNome());
				}
				
				if(membrosCiclo.size() == qtdMembros){
					ciclo++;
				}
			
			}
			data.add(Calendar.DAY_OF_MONTH, 1);
		}

		return listEncarregadoPao;
	}

	private SetMultimap<Integer, Membro> prepareMultimap(List<Membro> membros) {
		SetMultimap<Membro, Integer> multimap = HashMultimap
				.create();

		for (Membro membro : membros) {
			multimap.putAll(membro, membro.getDisponibilidade());
		}

		SetMultimap<Integer, Membro> inverse = Multimaps.invertFrom(multimap,
				HashMultimap.<Integer, Membro> create());
		return inverse;
	}

	private boolean isWorkDay(Calendar data) {
		int day = data.get(Calendar.DAY_OF_WEEK);
		return day != Calendar.SATURDAY && day != Calendar.SUNDAY;
	}
}
