package io.diegofernandes.clubedopao.model;

import java.util.List;

public class ListaEncarregadoPao {
	
	private List<Membro> membros;
	
	private List<DiaPao> listDiaPao;
	
	public ListaEncarregadoPao() {
	}
	
	

	public ListaEncarregadoPao(List<Membro> membros, List<DiaPao> listDiaPao) {
		super();
		this.membros = membros;
		this.listDiaPao = listDiaPao;
	}



	public List<Membro> getMembros() {
		return membros;
	}

	public void setMembros(List<Membro> membros) {
		this.membros = membros;
	}

	public List<DiaPao> getListDiaPao() {
		return listDiaPao;
	}

	public void setListDiaPao(List<DiaPao> listDiaPao) {
		this.listDiaPao = listDiaPao;
	}

}
