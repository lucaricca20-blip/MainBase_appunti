package com.betacom.process;

import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.betacom.enums.Reparto;
import com.betacom.objects.Impiegato;

import interfaces.ProcessInterface;

public class ProcessCollection implements ProcessInterface {

	@Override
	public boolean execute() throws Exception {
		System.out.println("Begin ProcessCollection");
		executeList();
		executeMap();
		mapTest ();
		return false;
	}
	/*
	 * LIST: simile all'ArrayList, oggi si usa di più la List, vedi tra qualche riga
	 */
	private void executeList () {
		List<Impiegato> lI = new ArrayList<Impiegato>();  //ho dovuto fare import da java.util per List
		
		lI.add(new Impiegato("Pietro", "Rossi", true, 1500.00, "PRODUZIONE"));
		lI.add(new Impiegato("Filippo", "Verdi", true, 1500.00, "IT"));
		lI.add(new Impiegato("Franco", "Giallo", true, 1500.00, "LOGISTICA"));
		lI.add(new Impiegato("Alessia", "Bianco", false, 1500.00, "LOGISTICA"));
		lI.add(new Impiegato("Lucia", "Grande", false, 1500.00, "PRODUZIONE"));
		lI.add(new Impiegato("Lorenzo", "Viola", true, 1500.00, "IT"));
		
		listImpiegati(lI, "Dopo creazione");
		
		Impiegato save = removeFromImpiegati(5, lI); //in save metto impiegato che rimuovo col metodo removeFromImpiegati
		if (save == null) {
			System.out.println("errore nella cancellazione");
		
		}
		listImpiegati(lI, "Dopo remove con posiz"); //Posso invocare il metodo senza creare istanza; il metodo è private quindi nella stessa classe posso usarlo senza istanze
		
		lI.add(save);
		listImpiegati(lI, "Dopo restore item cancellato");
		
		save = removeFromImpiegati("Viola", lI);
		listImpiegati(lI, "Dopo creazione con cognome");
		if (save == null) {
			System.out.println("errore nella cancellazione");
		}
		lI = updateStipendio("IT", lI, 1.5);
		listImpiegati(lI, "Dopo update Stipendio");
		
		lI.sort(new Comparator<Impiegato>() { //La classe sort ha bisogno di Comparator (che è un'interfaccia); all'interno del sort si mette un comparator
            /*
             * 
             * Sort ci permette di ordinare le liste, qui le ordiniamo in base al salario
             * Compare è l'unico metodo dell'interfaccia funzionale Comparator (le interfacce funzionali
             * contengono un solo metodo). Devo fare import per fare l'override
             */
			@Override
			public int compare(Impiegato o1, Impiegato o2) { //confronta due diversi oggetti impiegato in ingresso
				
				return Double.compare(o2.getSalary(), o1.getSalary()); // confronta salari per ordinarli
			}
		});
		listImpiegati(lI, "Dopo sort"); //ora vedo che ha ordinato dal salario più alto al più basso
	    
		
		System.out.println("*** forEach");	    
	    lI.forEach(i -> System.out.println(i)); //Posso usarlo invece del for per vedere il contenuto di una lista (non è importante). è una espressione lambda
	
	    /*
	     * Posso anche mettere il sort in un metodo, e lo metto nel metodo sortBySalary
	     * e lo chiamo di seguito (in basso vedi come lo implemento)
	     */
	    
	    
	    sortBySalary(lI); //è un metodo void, quindi non ha return e quindi lo chiamo così e non lI.sortBySalary
	    listImpiegati(lI, "Dopo chiamata metodo sortBySalary");
	    
	    
	    
	}
	
	private void executeMap () {   //MAP, fa parte delle collection di java
		System.out.println("Map process...");
		Map<String, String> map = new HashMap<String, String>(); //creo una map con due string. A interfaccia Map ho associato HashMap
		
		String value = null; //definisco stringa chiamata value con null
		String keySearch = null;
		
		
		map.put("k1", "v1"); //La prima è la chiave, la seconda il valore. Ricorda che map hanno chiave e valore
		map.put("k2", "v2");
		map.put("k3", "v3");
		map.put("k4", "v4");
		map.put("k5", "v5");
		map.put("k6", "v6");
		map.put("k7", "v7");
		map.put("k8", "v8");
		map.put("k9", "v9");
		map.put("k10", "v10");
		
		System.out.println("Numero elementi Map " + map.size());
		
		value = map.get("k7"); //alla stringa value assegno k7 così posso leggerne il valore
		System.out.println("Valore di k7: " +value); //vedrò che il suo valore è v7
		
		/*
		 * è case sensitive, distingue minuscole e maiuscole. Non troverebbe per es. V7
		 */
		
		keySearch = "k70"; //mi darà null, non la troverà
		keySearch = map.get(keySearch);
		
		if (map.containsKey(keySearch)) { //per vedere se contiene quella chiave
			value = map.get(keySearch);
			System.out.println("Valore di: " +keySearch + ":" + value);
	   } else {
		System.out.println("la chiave " + keySearch + " non trovata.");
	   }
		
	   value = "v5";
		if (map.containsValue(value)) {
			System.out.println("Valore di: " +value + " trovato");
	   } else {
		System.out.println("Valore di " + value + " non trovato.");
	   }
		
		
	   map.put("k3", "k3 modificata"); //ho modificato il valore mettendo ke modificata
	   System.out.println("Valore di k3: " + map.get("k3"));
	   
	   value = "k3"; //non lo trova, perché adesso è k3 modificata
		if (map.containsValue(value)) {
			System.out.println("Valore di: " +value + " trovato");
	   } else {
		System.out.println("Valore di " + value + " non trovato.");
	   }
		
		/*
		 * Entry vede la mappa con chiavi e valori. Col seguente for vogliamo visualizzare
		 * chiavi e valori
		 */
		
		System.out.println("List map with Entry: ");
		
		for (java.util.Map.Entry<String, String> it:map.entrySet()) { //per cercare nella Map scrivi Entri, poi ctrl+spazio e poi prendi quello relativo alle Map da java.util
			System.out.println("key: " + it.getKey() + " value:" + it.getValue());
		}
		
		/*
		 * Posso anche partire dalla chiave e usare KeySet. Voglio solo visualizzare la
		 * chiave (con keyset), ma metterò pure value
		 */
		System.out.println("List map with KeySet"); 
		
		for (String it:map.keySet()) {
			System.out.println("key: " + it + " value: " + map.get(it));
		}
		
	}
	
	private void mapTest () {
		System.out.println("*****Inizio mapTest*****");
		String params = "p1=aaa, p2=bbb, p3=26, p4=Paolo"; 
		/*
		 * Voglio traformare stringa params in una map. Dobbiamo scomporre questa stringa
		 * parametro per parametro e item per item (it).
		 */
		System.out.println("Valore di params: " +params);
		String [] p = params.split(","); //per scomporre parametro per parametro separando con virgola;
		
		for (String it:p) {
			System.out.println("Valori params: " +it);
		}
		System.out.println("Creazione della map: ");
		Map <String, String> map = new HashMap<String, String>(); //nuova Map di nome map
		
		for (String it:p) { 
			String [] elem = it.split("="); //devo dividere chiave e valore separandoli grazie a =
			map.put(elem[0], elem[1]);
		}
			
			System.out.println("List map with KeySet"); 
			
       for (String it:map.keySet()) {
	        System.out.println("key: " + it + " value: " + map.get(it));
            }

	    
		System.out.println("*****Inizio Esercizio*****");	
			
		String riga1 = "p1=aaa, p2=bbb, p3=26, p4=Paolo";
		String riga2 = "par1=10, par2=param2, par3=20.0, par4 =SAS";
		String riga3 = "k1=Terzo, k2 =50, k3=struccutura, k4=50%, k6=aaa";
		String riga4 = "p1=22, p2= Quarto, p3 = ABC,p4=20.5";
		
		
		/*
		//TODO List di map dei parametri (ogni riga è un elemento di una lista e ogni 
		//param...) Vuole una list di Map, deve essere quindi una sola.
			
		String [] r1 = riga1.split(",");
		String [] r2 = riga2.split(",");
		String [] r3 = riga3.split(",");
		String [] r4 = riga4.split(",");
		
		Map <String, String> map1 = new HashMap<String, String>();
		Map <String, String> map2 = new HashMap<String, String>();
		Map <String, String> map3 = new HashMap<String, String>();
		Map <String, String> map4 = new HashMap<String, String>();
		
		for (String it:r1) { 
			String [] elem = it.split("="); //devo dividere chiave e valore separandoli grazie a =
			map1.put(elem[0], elem[1]);
		}
		for (String it:r2) { 
			String [] elem = it.split("="); //devo dividere chiave e valore separandoli grazie a =
			map2.put(elem[0], elem[1]);
		}
		for (String it:r3) { 
			String [] elem = it.split("="); //devo dividere chiave e valore separandoli grazie a =
			map3.put(elem[0], elem[1]);
		}
		for (String it:r4) { 
			String [] elem = it.split("="); //devo dividere chiave e valore separandoli grazie a =
			map4.put(elem[0], elem[1]);
		}
		
		System.out.println("List map with KeySet"); 
		
	      for (String it:map1.keySet()) {
		        System.out.println("key: " + it + " value: " + map1.get(it));
	            }
	      for (String it:map2.keySet()) {
		        System.out.println("key: " + it + " value: " + map2.get(it));
	            }
	      for (String it:map3.keySet()) {
		        System.out.println("key: " + it + " value: " + map3.get(it));
	            }
	      for (String it:map4.keySet()) {
		        System.out.println("key: " + it + " value: " + map4.get(it));
	            }
	      
	      
	      
	      List<Map> array = new ArrayList<Map>();
	      array.add(map1);
	      array.add(map2);
	      array.add(map3);
	      array.add(map4);
	      
	      
	      //Manca parte per stampare;
	      
	      */
       
       List<String> input = new ArrayList<String>(); //Creo nuova List di stringhe
       input.add( "p1=aaa, p2=bbb, p3=26, p4=Paolo"); //aggiungo alla list varie stringhe
       input.add( "par1=10, par2=param2, par3=20.0, par4 =SAS");
       input.add( "k1=Terzo, k2 =50, k3=struccutura, k4=50%, k6=aaa");
       input.add( "p1=22, p2= Quarto, p3 = ABC,p4=20.5");
       
       System.out.println("Build map");
       /*
        * Col comando seguente credo una Lista di Map (considera che le Map hanno chiavi
        * e valori) chiamata res. In essa, dopo aver splittato le stringhe precedenti
        * secondo la virgola e secondo gli "=", metteremo tutto (p1 aaa p2 ecc).
        */
       List<Map<String, String>> res = new ArrayList<Map<String, String>>();
       for (String inp:input) { //For each: per ogni stringa (inp) nell list input fai ciò
    	   String[]par1 = inp.split(",");//metti in array par1 tutte le string splittate secondo la virgola
    	   
    	   /*
    	    * Il for principale quindi ha preso una riga intera e l'ha splittata secondo
    	    * la virgola, poi ogni pezzo (es. p1=aaa) lo ha messo dentro l'array par1
    	    * (in sostanza ha creato questo array ["p1=aaa", " p2=bbb", " p3=26", " p4=Paolo"]).
    	    * Di seguito, sempre all'interno del for principale, creo una mappa colum: essa
    	    * accoglierà i vari pezzi chiave e valore (p1 è chiave, aaa è valore) che otterremo
    	    * dallo split del for interno.
    	    */
    	   Map<String, String> colum = new HashMap<String, String>();
    	   
    	   /*
    	    * Adesso dobbiamo effettivamente riempirla questa mappa. Il primo for interno
    	    * servirà a riempire con le varie piccole stringhe la prima mappa.
    	    */
    	   for (String it:par1) { //for each stringa it contenuta in array par1
    		   String[] elem = it.split("="); //splitta ogni elemento secondo = e metti tutto in array elem
    		   /*
    		    * A questo punto ogni elemento è stato diviso, quindi abbiamo p1 aaa
    		    * Il p1 viene ovviamente messo all'indice 0 dell'array elem, mentre l'indice 1
    		    * viene occupato da aaa.
    		    * Con colum.put (put si usa per le mappe) dico di prendere l'elemento a indice
    		    * 0 e porlo come chiave (il trim serve per eliminare spazi inutili) e di
    		    * prendere l'elemento con indice 1 e metterlo come valore nella mappa.
    		    */
    		   colum.put(elem[0].trim(), elem[1].trim());
    		   
    		   /*
    		    * finisce così il primo ciclo del for interno; il for interno si ripete altre
    		    * tre volte finché tutta la riga non è stata splittata e collocata nella mappa
    		    * in chiavi e valori. 
    		    */
    		   
    	   }
    	   
    	   /*
    	    * Prima che il for principale passi alla seconda riga e riesegua tutto, la mappa
    	    * appena creata colum viene aggiunta a res. che è una lista di mappe.
    	    * Alla fine dell'ultimo ciclo del for principale, in res avremo 4 mappe.
    	    */
    	   res.add(colum);
    	   
       }
       System.out.println("Risultato.....");
       int i = 0;
       for(Map<String,String> it:res) { //per ogni mappa contenuta in res
    	   System.out.println("riga" + ++i +":" + it.size()); //serve solo a dire in che riga si trovava la stringa
    	   /*
    	    * Col seguente for interno gli facciamo stampare tutto ciò che c'è in ogni mappa.
    	    * Gli diciamo "per ogni chiave el contenuta nella mappa it", stampa la chiave;
    	    * ma grazie al metodo get (che nel caso delle mappe consente di leggere i 
    	    * valori) stampo pure i valori.
    	    */
    	   for (String el:it.keySet()) { //keySet mi permette di leggere la chiave
    		   System.out.println("key:" +el + " value:" + it.get(el));
    	   }
    	   /*
    	    * Finito il ciclo per la prima mappa, ricomincia il for principale e poi col
    	    * for interno stampo le altre mappe.
    	    */
       }   
	    
	}
	
	
	
	
	
	
	
	private void sortBySalary (List<Impiegato> lI) { //Posso metterlo anche come metodo per fare confronti
		lI.sort(new Comparator<Impiegato> () {
			@Override
			public int compare(Impiegato o1, Impiegato o2) { //confronta due diversi oggetti impiegato in ingresso
				
				return Double.compare(o2.getSalary(), o1.getSalary()); // confronta salari per ordinarli
			}
		});
	}
	
	private void listImpiegati (List<Impiegato> lI, String title) { 
		System.out.println("*********" + title + "******");
		int posiz = 0;
		for (Impiegato it:lI) {
			System.out.println(posiz + " " + it); //così ho posizione in cui si trova
			posiz++;
		}
		
		
	}
	private List<Impiegato> updateStipendio (String reparto, List<Impiegato> lI, double param ) { //devo incrementare stipendio di 1,5, mi deve ritornare la lista
		//String rep = Reparto.valueOf(reparto)); sarebbe stato più corretto scrivere così, per evitare che in ogni ciclo for debba convertire enum in stringa
		for (Impiegato it:lI) {
			
			if (it.getReparto() == Reparto.valueOf(reparto)) {
				it.setSalary(it.getSalary() * param);	//in param metterò 1,5 se voglio incrementare di 1,5, ma lo metto sopra quando chiamo il metodo	
				
		}
			
	}
		return lI; //ritorna la lista modificata
	}
	
	private Impiegato removeFromImpiegati (int posiz, List<Impiegato> lI ) { //Dopo private c'è Impiegato e non void perché voglio che mi torni un oggetto di tipo impiegato, infatti res lo è
		if (posiz > lI.size()) { //se posiz superiore a lunghezza lista
			System.out.println("posiz invalida:" + posiz + "max:" + lI.size());
			return null;
		}
		Impiegato res = lI.get(posiz);
		lI.remove(posiz); //rimuovo elemento in base alla posiz
		
		return res; //Il metodo restituisce elemento che rimuovo dalla lista
	}
	
	private Impiegato removeFromImpiegati (String cognome, List<Impiegato> lI ) {
		int posiz = 0;
		boolean isFound = false;
		
		for (Impiegato it:lI) {
			
			if (it.getCognome().equalsIgnoreCase(cognome)) {
				return removeFromImpiegati(posiz, lI); //Sto cercando posizione dentro la mia lista, se lo trova allora mette
				                                       //isFound, altrimenti null
			}
			posiz++;
			
		}
		return null;
	}

}
