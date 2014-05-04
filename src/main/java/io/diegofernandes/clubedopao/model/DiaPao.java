package io.diegofernandes.clubedopao.model;

import java.util.Date;

public class DiaPao {

	private Date data;
	
	private Integer diaDaSemana;
	
	private Integer semana;

	private Long idEncarregado;
	private String nomeEncarregado;

	public DiaPao() {
	}

	public DiaPao(final Date data,final Integer diaDaSemana, final Long idEncarregado,
			final String nomeEncarregado) {
		super();
		this.data = data;
		this.diaDaSemana = diaDaSemana;
		this.idEncarregado = idEncarregado;
		this.nomeEncarregado = nomeEncarregado;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(final Date data) {
		this.data = data;
	}

	public String getNomeEncarregado() {
		return this.nomeEncarregado;
	}

	public void setNomeEncarregado(final String nomeEncarregado) {
		this.nomeEncarregado = nomeEncarregado;
	}

	public Long getIdEncarregado() {
		return this.idEncarregado;
	}

	public void setIdEncarregado(final Long idEncarregado) {
		this.idEncarregado = idEncarregado;
	}

	@Override
	public String toString() {
		return "EncarregadoPao [data=" + data + ", idEncarregado="
				+ idEncarregado + ", nomeEncarregado=" + nomeEncarregado + "]";
	}

	public Integer getDiaDaSemana() {
		return diaDaSemana;
	}

	public void setDiaDaSemana(Integer diaDaSemana) {
		this.diaDaSemana = diaDaSemana;
	}

	public Integer getSemana() {
		return semana;
	}

	public void setSemana(Integer semana) {
		this.semana = semana;
	}
	

}
