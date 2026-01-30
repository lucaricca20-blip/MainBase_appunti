package com.betacom.objects;

import java.time.LocalDate;

import com.betacom.enums.Reparto;

public class Impiegato extends User {
	private Double salary;
	private Reparto reparto; //ho dovuto fare import dell'enum Reparto

	//Generate constructor with superclass (in source)
	

	public Impiegato(String nome, String cognome, Boolean sesso, Double salary) { //ha preso quelli della superclasse
		super(nome, cognome, sesso);
		
		this.salary = salary; //ho aggiunto io, così come Double salary nel costruttore
	}

	public Impiegato(String nome, String cognome, Boolean sesso, Double salary, String reparto) { //ha preso quelli della superclasse
		super(nome, cognome, sesso);
		
		this.salary = salary; //ho aggiunto io, così come Double salary e String reparto nel costruttore
		
		try {
		this.reparto = Reparto.valueOf(reparto); //da String a enum
		
		} catch (IllegalArgumentException e) {
			System.out.println("Errore: " + e.getMessage());
		}
		
	}
	
/*
	public Impiegato(String nome, String cognome, Boolean sesso, int anno, int mese, int giorno) {
		super(nome, cognome, sesso, anno, mese, giorno);
		// TODO Auto-generated constructor stub
	}


	public Impiegato(String nome, String cognome, Boolean sesso) {
		super(nome, cognome, sesso);
		// TODO Auto-generated constructor stub
	}


	public Impiegato(String nome, String cognome, String sesso, LocalDate dataNascita) {
		super(nome, cognome, sesso, dataNascita);
		// TODO Auto-generated constructor stub
	}
*/

	public Reparto getReparto() {
		return reparto;
	}


	public void setReparto(Reparto reparto) {
		this.reparto = reparto;
	}


	public Impiegato() {
		super();
	}


	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}
	
	
//	@Override  //l'ho creato io scrivendo pure override, prendendo il get da user e modificandolo
//	public Boolean getSesso() {
//		System.out.println("Il sesso dell'impiegato è " + (super.getSesso() ? "Maschio" : "Femmina"));
//		return super.getSesso(); //devo mettere super qui e nella riga precedente
//	}
	
	@Override //nel toString ho selezionato pure Inhereditary
	public String toString() {
		String resp = "Impiegato [salary="+ salary;
		if (reparto != null) { //evito così che toString non funzioni se non viene indicato il Reparto
		     //TODO visualizzare Reparto in String
			resp = resp + ", reparto= " + reparto.toString(); //da enum a String
			// resp = resp + "reparto= .........."
		}
		resp = resp + ", getNome()=" + getNome() + ", getCognome()=" + getCognome()
		+ ", getSesso()=" + getSesso() + "]";
			
		 	
		return resp; //ho cancellato una parte
	}

	
	
	
	
	

}
