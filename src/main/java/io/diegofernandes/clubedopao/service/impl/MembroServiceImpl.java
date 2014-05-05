package io.diegofernandes.clubedopao.service.impl;

import io.diegofernandes.clubedopao.model.DiaPao;
import io.diegofernandes.clubedopao.model.ListaEncarregadoPao;
import io.diegofernandes.clubedopao.model.Membro;
import io.diegofernandes.clubedopao.repository.MembroRepository;
import io.diegofernandes.clubedopao.service.MembroService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimaps;
import com.google.common.collect.Ordering;
import com.google.common.collect.SetMultimap;
import com.google.common.collect.Sets;
import com.google.common.collect.Sets.SetView;

@Service
public class MembroServiceImpl implements MembroService {

	private final static int QTD_DIAS = 30;

	@Autowired
	private MembroRepository membroRepository;

	private final static Ordering<Membro> ORDERING = new Ordering<Membro>() {

		@Override
		public int compare(final Membro left, final Membro right) {
			return left.getDisponibilidade().size()
					- right.getDisponibilidade().size();
		}
	};

	@Override
	public ListaEncarregadoPao gerarListaEncarregadosPao(final Date dataInicio) {
		final List<Membro> membros = this.membroRepository.findAll();
		final int qtdMembros = membros.size();

		final List<DiaPao> listDiaPao = new ArrayList<DiaPao>();
		final ListaEncarregadoPao listaEncarregadoPao = new ListaEncarregadoPao(
				membros, listDiaPao);
		final Calendar data = Calendar.getInstance();
		data.setTime(dataInicio);

		final Calendar dataFim = (Calendar) data.clone();
		dataFim.add(Calendar.DAY_OF_MONTH, QTD_DIAS);

		final SetMultimap<Integer, Membro> inverse = this
				.prepareMultimap(membros);

		final SetMultimap<Integer, Membro> cicloMembros = HashMultimap.create();

		int dayOfWeek;
		int ciclo = 1;
		DiaPao diaPao;
		while (data.compareTo(dataFim) <= 0) {

			if (this.isWorkDay(data)) {
				dayOfWeek = data.get(Calendar.DAY_OF_WEEK);
				diaPao = new DiaPao();
				listDiaPao.add(diaPao);
				diaPao.setData(data.getTime());
				diaPao.setDiaDaSemana(dayOfWeek);
				diaPao.setSemana(data.get(Calendar.WEEK_OF_YEAR));
				final Set<Membro> membrosDia = inverse.get(dayOfWeek);
				final Set<Membro> membrosCiclo = cicloMembros.get(ciclo);

				final SetView<Membro> membrosDisponiveis = Sets.difference(
						membrosDia, membrosCiclo);

				if (!membrosDisponiveis.isEmpty()) {
					final Membro membro = ORDERING.min(membrosDisponiveis);
					cicloMembros.put(ciclo, membro);
					diaPao.setIdEncarregado(membro.getId());
					diaPao.setNomeEncarregado(membro.getNome());
				}

				if (membrosCiclo.size() == qtdMembros) {
					ciclo++;
				}

			}
			data.add(Calendar.DAY_OF_MONTH, 1);
		}

		return listaEncarregadoPao;
	}

	private SetMultimap<Integer, Membro> prepareMultimap(
			final List<Membro> membros) {
		final SetMultimap<Membro, Integer> multimap = HashMultimap.create();

		for (final Membro membro : membros) {
			multimap.putAll(membro, membro.getDisponibilidade());
		}

		final SetMultimap<Integer, Membro> inverse = Multimaps.invertFrom(
				multimap, HashMultimap.<Integer, Membro> create());
		return inverse;
	}

	private boolean isWorkDay(final Calendar data) {
		final int day = data.get(Calendar.DAY_OF_WEEK);
		return day != Calendar.SATURDAY && day != Calendar.SUNDAY;
	}
}
