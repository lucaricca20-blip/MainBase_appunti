package com.betacom.models;

public class RapportoCliente {
	
	private Integer idRapporto;
	private String descrizione;
	private Integer idCliente;
	private Integer idDipendente;
	public Integer getIdRapporto() {
		return idRapporto;
	}
	public void setIdRapporto(Integer idRapporto) {
		this.idRapporto = idRapporto;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public Integer getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}
	public Integer getIdDipendente() {
		return idDipendente;
	}
	public void setIdDipendente(Integer idDipendente) {
		this.idDipendente = idDipendente;
	}
	public RapportoCliente() {
		super();
	}
	public RapportoCliente(Integer idRapporto, String descrizione, Integer idCliente, Integer idDipendente) {
		super();
		this.idRapporto = idRapporto;
		this.descrizione = descrizione;
		this.idCliente = idCliente;
		this.idDipendente = idDipendente;
	}
	@Override
	public String toString() {
		return "RapportoCliente [idRapporto=" + idRapporto + ", descrizione=" + descrizione + ", idCliente=" + idCliente
				+ ", idDipendente=" + idDipendente + "]";
	}
	public RapportoCliente(String descrizione, Integer idCliente, Integer idDipendente) {
		super();
		this.descrizione = descrizione;
		this.idCliente = idCliente;
		this.idDipendente = idDipendente;
	}
	

}
