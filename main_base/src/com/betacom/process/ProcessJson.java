package com.betacom.process;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.List;

import com.betacom.objects.ObjectJson;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import interfaces.ProcessInterface;

public class ProcessJson implements ProcessInterface {


	/*
	 * .json è un formato per scambiare dati in modo
	 * semplice e leggibile, indipendentemente dal
	 * linguaggio. Molto usato, per esempio, per 
	 * API Rest. Significa JavaScript Object notions.
	 * 
	 * Si mettono nei json gli oggetti tra due parentesi graffe.
	 * Si mettono dentro dei tag (come nome), come
	 * vedi in basso.
	 * Supporta String, Integer, Boolean, oggetti
	 *(come "anagrafica" in basso).
	 * 
	 * 
	 * [
	 * {
	 * 
	 *  "nome":"pippo",
	 *  "eta" : 25, //un parametro può essere null
	 *  "attivo" : true,
	 *  "anagrafica" : {
	 *         "data_nascita" : "27/09/1996",
	 *         "Luogo_nascita" : "Torino"
	 *  }
	 *
	 * },
	 * {
	 * 
	 *  "nome":"pluto",
	 *  "eta" : 23,
	 *  "attivo" : true,
	 *  "anagrafica" : {
	 *         "data_nascita" : "27/09/2006",
	 *         "Luogo_nascita" : "Roma"
	 *  }
	 *
	 * }
	 * 
	 * ]
	 * 
	 * Devo saper trasformare una lista di oggetti
	 * in json e viceversa. Devo scaricare un parse per json.
	 * Vado su https://repo1.maven.org/maven2/com/google/code/gson/gson/2.13.2/
	 * e scarico GSON 2.13.2.jar e lo colloco in una
	 * cartella dove raccolgo tutte le librerie (è
	 * indifferente dove sia).
	 * Dopo clicco sulla cartella del progetto
	 * su Eclipse, poi build path, cofigure build path
	 * libraries, classpath, add exsternal jars,
	 * seleziono quello che ho scaricato e
	 * poi faccio apply and close.
	 * 
	 * Solo dopo ciò posso fare le operazioni
	 * seguenti, cioè il parse da json a oggetti
	 * e viceversa
	 */
	
	@Override
	public boolean execute() throws Exception {
		System.out.println("Begin ProcessJson");
		
		/*
		 * Creo oggeto User per poterlo trasformare in json
		 */
		ObjectJson usr = new ObjectJson("Giuseppe", "Verdi", true);
		
		/*
		 * Posso creare l'oggetto Gson in due modi, o con il GsonBuilder.setPrettyPrinting o senza (come quello che ho messo
		 * commentato), ma conviene il primo perché restituisce nella console la visualizzazione classica verticale dei json.
		 */
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		// Gson gson = new Gson();     //import da com.google.gson (se ho installato il jars)
		
		String jsonString = gson.toJson(usr); //da un oggetto di una normale classe ObjectJson lo fa diventare json
		
		/*
		 * Da oggetto a json
		 */
		
		System.out.println(jsonString);
		
		/*
		 * Stamperà così:
		 * {"nome":"Giuseppe","cognome":"Verdi","sesso":true}
		 */
		
		
		/*
		 * Da json a oggetto
		 */
		
		ObjectJson newUsr = gson.fromJson(jsonString, ObjectJson.class); //voglio che con le info di jsonString crei un nuovo oggetto, e nel secondo posto indico in quale classe, cioè ObjectJson
		
		System.out.println("new object creato da Json: " + newUsr);
		
		/*
		 * Creiamo un json da una list
		 */
		
		List<ObjectJson> lUser = List.of(
				new ObjectJson("Giuseppe", "Verdi", true),
				new ObjectJson("Paolo", "Paluco", true),
				new ObjectJson("Anna", "Bella", true)
				
				);
		
		System.out.println("List json *********");
		jsonString = gson.toJson(lUser);
		System.out.println(jsonString);
				
		/*
		 * Da json a List
		 */
		
		Type type = new TypeToken<List<ObjectJson>>() {}.getType();  //retrieve correct type
		List<ObjectJson> lUser1 = gson.fromJson(jsonString, type);
		lUser1.forEach(u -> System.out.println(u));
		
		return false;
	}
	

}
