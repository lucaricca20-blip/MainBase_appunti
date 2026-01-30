package com.betacom.objects;

public class ImpiegatoStream {
	
	private String nome;
	private String cognome;
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
	public ImpiegatoStream(String nome, String cognome) {
		super();
		this.nome = nome;
		this.cognome = cognome;
	}
	public ImpiegatoStream() { //costruttore vuoto
		super();
	}
	@Override
	public String toString() {
		return "ImpiegatoStream [nome=" + nome + ", cognome=" + cognome + "]";
	}
	
	
	

	
}
