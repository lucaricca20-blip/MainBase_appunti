package com.betacom;

import java.util.ArrayList;

import com.betacom.objects.Gazzella;
import com.betacom.objects.Leone;
import com.betacom.objects.Pesce;

import interfaces.Preda;
import interfaces.Predatore;
import interfaces.SampleInterface;
import interfaces.SampleInterface1;

public class MainEsercizio1 {

	public static void main(String[] args) {
		
		/*
		Predatore l = new Leone ();
		Predatore p = new Pesce ();
		Preda p1 = new Pesce ();
		Preda g = new Gazzella ();
		
		l.sonoUnPredatore();
		p.sonoUnPredatore();
		p1.sonoUnaPreda();
		g.sonoUnaPreda();

		*/
		
		/*
		 * Nell'arrayList seguente invece che stringhe o interi metto Objects. Con .add aggiungo
		 * un nuovo oggetto Leone, uno Gazzella ecc Con il for each scorro l'array e per ogni elemento
		 * uso il metodo identificate che ho scritto in basso dopo al main
		 */
		ArrayList<Object> l0 = new ArrayList<Object>(); 
		l0.add(new Leone());
		l0.add(new Gazzella());
		l0.add(new Pesce());
		
		for (Object obj:l0) {
			identificate (obj);
		}
		
		/*
		 * In alternativa posso fare come di seguito
		 */
		identificate( new Leone()); //esegue il metodo identificate dichiarato dopo il main, usando un oggetto Leone
		identificate( new Gazzella());
		identificate( new Pesce());
		
		

		
       /*
        * Di seguito il metodo identificate, che verifica che tipo di oggetto viene passato,
        * fa il cast per leggerlo come tale, e infine esegue su di esso il metodo definito in quella classe
        * (a sua volta ereditato dall'interfaccia).
        */
	}
	private static void identificate (Object o) { //Object accetta qualsiasi tipo di oggetto (il sistema non sa ancora cosa gli viene passato) 
	    if(o instanceof Preda) { //Se questo oggetto è un'istanza di Preda ,allora vai avanti
	    	Preda obj = (Preda)o; //Allora converti questo oggetto generico Object in tipo Preda e assegnalo a obj
	    	obj.sonoUnaPreda(); //esegui il metodo sonoUnaPreda come definito nella classe di quell'oggetto
	    }
	    	
	    
		if(o instanceof Predatore) {
		Predatore obj = (Predatore) o;
		obj.sonoUnPredatore();
	    
	    }
}
}
