package com.betacom.process;

import java.util.ArrayList;

import com.betacom.enums.Reparto;
import com.betacom.exceptions.AcademyException;
import com.betacom.objects.Impiegato;

import interfaces.ProcessInterface;

public class ProcessEnum implements ProcessInterface {

	@Override
	public boolean execute() throws Exception { //è il main di MainProcess che lo fa funzionare
		System.out.println("Begin Process ENUM");
		
		String valore = "PRODUZIONE";
		try {
			/*
			 * valueOf è un metodo statico che gli enum hanno tutti e serve a convertire
			 * una stringa nel valore dell'enum corrispondente. valore è una string e 
			 * java cerca nell'enum corrispondente (Reparto) una costante con lo stesso
			 * nome: se lo trova restituisce quel valore, altrimenti lancia eccezione
			 */
		    Reparto reparto = Reparto.valueOf(valore); 
		
		    System.out.println("Reparto:" + reparto.toString());
		} catch (IllegalArgumentException e) {
			throw new AcademyException(e.getMessage());
		}
		
		// arraylist impiegati con reparto caricato
		// definire un criterio di selezione per esempio "tutti gli impiegati che sono in
		// PRODUZIONE, fare un ciclo per stampare tutti gli impiegati che corrispondono
		//alla selezione
		
		ArrayList <Impiegato> listaImpiegati = new ArrayList<Impiegato>();
		
		/*
		 * Adesso devo riempire la lista con tanti oggetti Impiegato
		 */
		listaImpiegati.add(new Impiegato("Pietro", "Rossi", true, 1500.00, "PRODUZIONE"));
		listaImpiegati.add(new Impiegato("Filippo", "Verdi", true, 1500.00, "IT"));
		listaImpiegati.add(new Impiegato("Franco", "Giallo", true, 1500.00, "LOGISTICA"));
		listaImpiegati.add(new Impiegato("Alessia", "Bianco", false, 1500.00, "LOGISTICA"));
		listaImpiegati.add(new Impiegato("Lucia", "Grande", false, 1500.00, "PRODUZIONE"));
		listaImpiegati.add(new Impiegato("Lorenzo", "Viola", true, 1500.00, "IT"));
		
		
		
		String search = "PRODUZIONE"; //Qui posso scrivere PRODUZIONE, IT O LOGISTICA
		
		
		for (Impiegato imp:listaImpiegati) { //deve scorrere gli elementi Impiegato dell'arraylist listaImpiegati
			if (imp.getReparto() == Reparto.valueOf(search)) //valueOf va a vedere nell'enum se c'è "PRODUZIONE" o comunque ciò che ho messo in search e lo confronta con quello dei vari impiegati
				/*
				 * Attento: se confronti due cose dello stesso tipo (es. due enum, due stringhe)
				 * puoi usare ==, altrimenti usa .equals.
				 * In questo caso stavamo confrontando due enum e va bene, perché in classe
				 * Impiegato Reparto è un enum (private Reparto = private).
				 * Altrimenti avrei potuto scrivere:
				 * if(imp.getReparto().toString().equals(search))				
				 */			
				System.out.println("Impiegato nel reparto: " +search + "," +imp);
			
			
		}
		

		return false;
	}

}
