package com.betacom.objects;

import java.time.LocalDate;

public class User {
	
	/*
	 * public: visibile ovunque;
	 * private: visibile solo all'interno della stessa classe;
	 * protected: visibile dalle classi figlie e dallo stesso package;
	 * default: visibile solo all'interno dello stesso package (non si scrive niente prima dell'attributo).
	 * 
	 */
	
	private String nome;
	private String cognome;
	private Boolean sesso;
	private LocalDate dataNascita;
	private LocalDate dataCertificatoMedico;
	private Integer meseDiValidita;

	
	public LocalDate getDataCertificatoMedico() {
		return dataCertificatoMedico;
	}
	public void setDataCertificatoMedico(LocalDate dataCertificatoMedico) {
		this.dataCertificatoMedico = dataCertificatoMedico;
	}
	public Integer getMeseDiValidita() {
		return meseDiValidita;
	}
	public void setMeseDiValidita(Integer meseDiValidita) {
		this.meseDiValidita = meseDiValidita;
	}
	public LocalDate getDataNascita() {
		return dataNascita;
	}
	public void setDataNascita(LocalDate dataNascita) {
		this.dataNascita = dataNascita;
	}

	private String test;
	

	public User() {  //è un esempio di overloading, è un costruttore senza parametri
		super();
	}
	//Genero costruttori con Source e poi Generate....
	public User(String nome, String cognome, Boolean sesso) {
		super();
		this.nome = nome; //il this ci dice che è un attributo della stessa classe
		this.cognome = cognome;
		this.sesso = sesso;
	}
	
	public User(String nome, String cognome, Boolean sesso, int anno, int mese, int giorno) {
		super();
		this.nome = nome; //il this ci dice che è un attributo della stessa classe
		this.cognome = cognome;
		this.sesso = sesso;
		this.dataNascita = LocalDate.of(anno, mese, giorno); //mi permette di inserire anno mese e giorno
	}
	
	//Faccio di seguito overloading del costruttore (cioè ne metto un altro), ma modifico sesso: metto String invece di boolean
	//potrò allora scegliere se mettere in sesso true/false (quindi il boolean del precedente costruttore
	//oppure M/F come invecede dice il costruttore che segue
	
	public User(String nome, String cognome, String sesso, LocalDate dataNascita) {
		super();
		this.nome = nome; //il this ci dice che è un attributo della stessa classe
		this.cognome = cognome;
		this.sesso = ("M".equals(sesso)) ? true:false;
		this.dataNascita = dataNascita;
		
		
	}
	
	public User(String nome, String cognome, String sesso, int anno, int mese, int giorno) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.sesso = ("M".equals(sesso)) ? true : false;
		this.dataNascita = LocalDate.of(anno, mese, giorno);
	}
	
	


	public String getNome() { //GET serve a leggere la variabile, è un metodo pubblico (pure SET)
		return nome;
	}

	public void setNome(String nome) { //se non uso il costruttore, posso usare questo per valorizzare attributi
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
	@Override
	public String toString() {
		return "User [nome=" + nome + ", cognome=" + cognome + ", sesso=" + sesso + ", dataNascita=" + dataNascita
				+ ", dataCertificatoMedico=" + dataCertificatoMedico + ", meseDiValidita=" + meseDiValidita + ", test="
				+ test + "]";
	}


	
	

}
