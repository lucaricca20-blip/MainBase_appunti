package com.betacom.process;

import java.util.StringTokenizer;

import interfaces.ProcessInterface;

public class StringProcess implements ProcessInterface {
	
	public boolean execute () { //Questo metodo viene utilizzato in MainProcess.java
		System.out.println("Begin StringProcess");
		String nome = "Gerard";
		StringBuilder sb = new StringBuilder(); //è comodo quando devo mettere assieme più String, evitando di fare concatenazioni con i +
		/*
		 * build della String con append
		 */
		sb.append("Buongiorno");
		sb.append(", ");
		sb.append ("sono ");
		sb.append(nome);
		
		String rS = sb.toString();
		System.out.println(rS);
		
		/*
		 * build della String con String.format
		 */
		String n = "Francese";
		String r = String.format("Mio nome è %s, e sono %s", nome, n);
		System.out.println(r);
		
		if (nome.equalsIgnoreCase("gerard")) //equalsIgnoreCase serve per verificare se nome è uguale alla parola che scrivo tra parentesi, ignorando lettere maiuscole o minuscole
			System.out.println("Found" + nome);  //se invece non voglio ignorare lettere minuscole o maiuscole, scrivo nome.equals("gerard);
		
		if (rS.contains(","))
			System.out.println("Found comma");
		else
			System.out.println("Not Found comma");
		
		String test = "      ".trim(); // trim serve per eliminare spazi vuoti
		if (test.isEmpty()) //per verificare se è vuota
			System.out.println("String vuota");
		
		/*
		 * String compare
		 */
		String p1 = "Samsung";
		String p2 = "Samsung";
		int result = p2.compareTo(p1); //Comparirà 0 se sono uguali, altrimenti altro numero 
		
		System.out.println("compare = " + result);
		
		/*
		 * trasformazione int to string
		 */
		
		int numero = 20;
		String numeroStr = String.valueOf(numero); //serve per passare da int a String. Fa parte della classe String, e si può passare anche da float a string ecc
		System.out.println("Numero string" +numeroStr); //non vedo differenze, ma ha trasformato
		
		/*
		 * trasformazione String to Integer
		 */
		
		numeroStr = "457592";
		numero = Integer.parseInt(numeroStr); //da String a Integer
		numero++; //verifico se somma, per capire se ha trasformato in Integer
		System.out.println("Valore di numero:" +numero);
		
		/*
		 * Trasformazione String in array
		 */
		
		String parameters = "token1,token2, token3, token4, token5, token6,token7 ";
		String [] tokens = parameters.split(","); //Posso così separare i vari pezzi separati da una virgola. Ogni token sarà un elemento dell'array
		for (String it:tokens) {
			System.out.println(">>" + it.trim() + "<<");// trim elimina spazi
		}
		
		/*
		 * StringTokenizer
		 */
		
		System.out.println("***** Utilizzo di StringTokenizer *****");
		StringTokenizer st = new StringTokenizer (parameters, ","); //devo fare import di StringTokenizer
		while (st.hasMoreTokens() ) {
			String txt = st.nextToken().trim();
			System.out.println(">>" + txt + "<<"); //visivamente ottengo lo stesso risultato di prima, ma con i Tokenizer
		}
		
		/*
		 * Substring
		 */
		System.out.println(r.substring(5)); //Parte direttamente dopo il quinto carattere della stringa contenuta in r
		
		System.out.println(r.substring(11,17 )); //prende i caratteri intermedi della stringa secondo numeri indicati
			
		System.out.println(r.substring(r.indexOf("G")));//simile alla riga 91, ma index of fa in modo che si fermi al primo G
		
		System.out.println(r.substring(r.indexOf("G"), r.indexOf(",") )); // così inizia alla prima G e si ferma prima della prima virgola
		
		/*
		 * Replace
		 */
		String prova = "questo è un test per il linguaggio ££, Vediamo il risultato";
		String provatR = prova.replace("££", "Java"); //Voglio sostituire i ££ con Java
		String provatRi = prova.replaceFirst("££", "Java").toUpperCase().trim(); //toUpperCase mette tutto in maiuscolo
		
		System.out.println(provatR);
		System.out.println(provatRi);
		
		
		return true;
	}

}
