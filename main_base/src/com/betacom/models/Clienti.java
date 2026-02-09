package com.betacom.models;

public class Clienti {
	private Integer id_cliente;
	private String denominazione;
	private String pIva;
	private String indirizzo;
	private String telefono;
	private Integer idComune;


	
	public Integer getId_cliente() {
		return id_cliente;
	}
	public void setId_cliente(Integer id_cliente) {
		this.id_cliente = id_cliente;
	}
	public String getDenominazione() {
		return denominazione;
	}
	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}
	public String getpIva() {
		return pIva;
	}
	public void setpIva(String pIva) {
		this.pIva = pIva;
	}
	public String getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public Integer getIdComune() {
		return idComune;
	}
	public void setIdComune(Integer idComune) {
		this.idComune = idComune;
	}

	@Override
	public String toString() {
		return "Clienti [id_cliente=" + id_cliente + ", denominazione=" + denominazione + ", pIva=" + pIva
				+ ", indirizzo=" + indirizzo + ", telefono=" + telefono + ", idComune=" + idComune + "]";
	}
	public Clienti() {
		super();
	}
	public Clienti(Integer id_cliente, String denominazione, String pIva, String indirizzo, String telefono,
			Integer idComune) {
		super();
		this.id_cliente = id_cliente;
		this.denominazione = denominazione;
		this.pIva = pIva;
		this.indirizzo = indirizzo;
		this.telefono = telefono;
		this.idComune = idComune;
	}
	public Clienti(String denominazione, String pIva, String indirizzo, String telefono, Integer idComune) {
		super();
		this.denominazione = denominazione;
		this.pIva = pIva;
		this.indirizzo = indirizzo;
		this.telefono = telefono;
		this.idComune = idComune;
	}


}
