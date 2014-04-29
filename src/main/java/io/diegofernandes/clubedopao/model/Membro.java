package io.diegofernandes.clubedopao.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "MEMBRO", uniqueConstraints = @UniqueConstraint(columnNames = {
		"ID", "EMAIL" }))
@NamedQueries({ @NamedQuery(name = "Membro.findByEmail", query = "select o from Membro o where o.email like :email"),
	@NamedQuery(name="Membro.findByNomeOrEmail", query = "select o from Membro o where lower(o.nome) like lower(:filter) or  lower(o.email) like lower(:filter)")})
public class Membro implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1764207619350677654L;

	@Id
	@GeneratedValue
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "NOME", nullable = false)
	private String nome;

	@Column(name = "EMAIL", nullable = false)
	private String email;

	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name = "MEMBRO_DISPONIBILIDADE", joinColumns = @JoinColumn(name = "MEMBRO_ID"))
	@Column(name = "DISPONIBILIDADE")
	private List<Integer> disponibilidade;

	public Membro() {

	}

	public Membro(final Long id, final String nome, final String email) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
	}

	public Membro(final Long id, final String nome, final String email,
			final List<Integer> disponbilidade) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.disponibilidade = disponbilidade;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(final String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public List<Integer> getDisponibilidade() {
		return this.disponibilidade;
	}

	public void setDisponibilidade(final List<Integer> disponbilidade) {
		this.disponibilidade = disponbilidade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((this.disponibilidade == null) ? 0 : this.disponibilidade
						.hashCode());
		result = prime * result
				+ ((this.email == null) ? 0 : this.email.hashCode());
		result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
		result = prime * result
				+ ((this.nome == null) ? 0 : this.nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		final Membro other = (Membro) obj;
		if (this.disponibilidade == null) {
			if (other.disponibilidade != null) {
				return false;
			}
		} else if (!this.disponibilidade.equals(other.disponibilidade)) {
			return false;
		}
		if (this.email == null) {
			if (other.email != null) {
				return false;
			}
		} else if (!this.email.equals(other.email)) {
			return false;
		}
		if (this.id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!this.id.equals(other.id)) {
			return false;
		}
		if (this.nome == null) {
			if (other.nome != null) {
				return false;
			}
		} else if (!this.nome.equals(other.nome)) {
			return false;
		}
		return true;
	}

}
