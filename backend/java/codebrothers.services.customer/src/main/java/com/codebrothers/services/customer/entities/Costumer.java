package com.codebrothers.services.customer.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;;

@Entity
@Table(name = "tb_costumer")
public class Costumer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome" , length=30)
	@NotNull
	private String nome;
	
	@Column(name = "sobrenome", length = 50)
	private String sobrenome;
	
	@Column(name = "rg", length = 12)
	private String rg;
	
	@Column(name = "cpf", length = 14)
	@NotNull
	private String cpf;
	
	@Column(name = "ativo", columnDefinition = "boolean default true")
	@NotNull
	private Boolean ativo;	
	
	@Column(name = "datanascimento")
	private Date dataNascimento;
	
	@Column(name = "criadoem", columnDefinition = "DATE DEFAULT CURRENT_DATE")
	@NotNull
	private Date criadoEm;
	
	@Column(name = "atualizadoem")
	private Date atualizadoEm;
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Date getCriadoEm() {
		return criadoEm;
	}

	public void setCriadoEm(Date criadoEm) {
		this.criadoEm = criadoEm;
	}

	public Date getAtualizadoEm() {
		return atualizadoEm;
	}

	public void setAtualizadoEm(Date atualizadoEm) {
		this.atualizadoEm = atualizadoEm;
	}
				
	public Costumer() {
	    
	}

	public Costumer(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}