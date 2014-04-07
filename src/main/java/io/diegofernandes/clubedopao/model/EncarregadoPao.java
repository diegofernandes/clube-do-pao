package io.diegofernandes.clubedopao.model;

import java.util.Date;

public class EncarregadoPao {

	private Date data;

	private Long idEncarregado;
	private String nomeEncarregado;

	public EncarregadoPao() {
	}

	public EncarregadoPao(final Date data, final Long idEncarregado,
			final String nomeEncarregado) {
		super();
		this.data = data;
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

}
