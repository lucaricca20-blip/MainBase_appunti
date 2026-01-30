package com.betacom.objects;

import java.util.List;

public class GenericResponse<T, U> {
	
	/*
	 * 	I Generics permettono di scrivere classi, metodi e interfacce
	 *  riutilizzabili mantenendo type-safety (sicurezza dei tipi).
	 *  In pratica: scrivi una volta la classe, decidi dopo che tipo di oggetti userà.
	 *  è come se io scrivessi : Scatola <T>, un cui T potrebbe essere qualsiasi cosa
	 *  dirò dopo a Java in effetti cos'è.
	 *  
	 *  In alto, dopo GenericResponse, ho messo T e U: non sono dei parametri reali
	 *  ma sono parametri tipo (utilizzerò T per i dati (data) e U per i messaggi (msg),
	 *  ma glielo dirò nella classe che mi interessa, e in questo caso nella classe
	 *  ProcessGenerics.
	 *  
	 *  Adesso definisco gli attributi della classe.
	 */
	
	private Boolean rc;
	private U msg;
	List<T> data;
	
	//Metto setter e getter
	
	public Boolean getRc() {
		return rc;
	}
	public void setRc(Boolean rc) {
		this.rc = rc;
	}
	/*
	 * Il getter di Msg lo modifico dicendogli che il tipo generico di msg
	 * è U, quindi ancora indefinito. Stessa cosa nel setter.
	 */
	public U getMsg() {
		return msg;
	}
	public void setMsg(U msg) {
		this.msg = msg;
	}
	
	/*
	 * Anche qui, non dico ancora cosa metterò nelle List "Data",
	 * metto per il momento un generico T dentro.
	 */
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		return "GenericResponse [rc=" + rc + ", msg=" + msg + ", data=" + data + "]";
	}
	
	/*
	 * Adeguo il costruttore con U e T
	 */
	public GenericResponse(Boolean rc, U msg, List<T> data) {
		super();
		this.rc = rc;
		this.msg = msg;
		this.data = data;
	}
	public GenericResponse() {
		super();
	}

}