package com.betacom.objects;

import java.time.LocalDate;

public class ObjectJson {
	
	private String nome;
	private String cognome;
	private Boolean sesso;
	
	
	
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public Boolean getSesso() {
		return sesso;
	}
	public void setSesso(Boolean sesso) {
		this.sesso = sesso;
	}

	public ObjectJson(String nome, String cognome, Boolean sesso) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.sesso = sesso;
	}
	public ObjectJson() {
		super();
	}
	@Override
	public String toString() {
		return "ObjectJson [nome=" + nome + ", cognome=" + cognome + ", sesso=" + sesso + "]";
	}
	
	

}
