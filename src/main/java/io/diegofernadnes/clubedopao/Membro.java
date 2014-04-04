package io.diegofernadnes.clubedopao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="MEMBRO")
public class Membro implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1764207619350677654L;
	
	@Id
	@GeneratedValue
	@Column(name="ID",nullable=false)
	private Long id;
	
	@Column(name="NOME",nullable=false)
	private String nome;
	
	@Column(name="EMAIL",nullable=false)
	private String email;
	
	@ElementCollection
	@Column(name="DISPONBILIDADE")
	private List<DayOfWeek> disponbilidade;

}
