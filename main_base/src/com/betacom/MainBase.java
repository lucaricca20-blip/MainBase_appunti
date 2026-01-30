package com.betacom;

import java.util.ArrayList;
import java.util.Scanner;

import com.betacom.objects.User;
import com.betacom.utils.PromozioneAutomatica;

public class MainBase {

	public static void main(String[] args) {
		System.out.println("Ciao_da_main");
		/*
		 * primitive
		 * nome     bit      byte      range    valori
		 * byte      8         1        -128     +127
		 * int       32        4        + p- 2*10^9
		 * short     16        2        -32768 +32767
		 * long      64        8        + o - 9*10^18
		 * float     32        4        
		 * double    64        8        
		 */
		byte b = 127;          // Wrapped = Byte
		int i = 2_000_000_000; // Wrapped = Integer
		short s = 32767;       // Wrapped = Short
		long l = 543_456_456;  // Wrapped = Long
		char c = 'z';          // Wrapped = Character
		boolean bool = true;  //Wrapped: Boolean
		double d = 123;
		
		/*
		 * referenze
		 */
		String str = "Ciao sono una string";
		System.out.print(str + "\n"); // ho usato print per la stringa, non println; il + serve per concatenare
		
		
		Scanner sc = new Scanner(System.in);  //Inizio a scrivere Sc poi clicco su ctrl+spazio e mi suggerisce classe Scanner, e mi fa l'import di java.util ecc
		System.out.print("nome:");
		String nome = sc.nextLine();
		System.out.println("Valore di nome:" +nome.toUpperCase());  // Questo metodo permette di scrivere tutto in maiuscolo
		
		/*
		 * operatori logici
		 * && and
		 * || or
		 * ! not
		 * < > <= >=
		 */
		char p3 = 'p';
		int p1 = 10;
		int p2 = 16;
		
		boolean rc = (p1 < p2) && (p3 != 'p');
		System.out.println("valore di rc:" + rc);
		
		int eta = 30;
		String result = "";
		if (eta < 30) 
			result = "Sei un ragazzino";
		 else 
			result = "Non sei un ragazzino";
		
		
		System.out.println(result);
		
		// ternary expression
		
		result = (eta < 30) ? "Sei un ragazzino (op ternary)" : "Non sei un ragazzino"; //operatore ternario: se è vera scrivi "Sei un ragazzino" altrimenti .....
		System.out.println(result);
		
		/*
		 * operatori aritmetici
		 * + - / * %
		 */
		
		i = 20;
		i++; // (i = i + 1)
		
		int jj = 3;
		int res = i * jj;
		int div = res/2;
		int rim = res%2;
		
		System.out.println("Valore di i:" + i
				+ " res:" + res
				+ " div:" +div
				+ " rim:" +rim);
		
		String param = "123456";
		i = Integer.parseInt(param); //con questo metodo di Integer posso trasformare la String in Int, se non ci sono lettere ma solo numeri nella string
		System.out.println("Conversione string to int: " + param);
		
		param = "slnflnlsdovb";
		System.out.println("lenght param: " + param.length() // questo metodo della classe String mi dice quanti caratteri ci sono nella stringa
		        + "part of string: " + param.substring(4,6)); // questo metodo prende solo una parte della stringa
		param = "     inizio     corso di  Java            ";
		System.out.println(">>>" +param.trim() + "<<<"); //Serve .trim per togliere spazi nelle stringhe
		
		String search = "corso";
		if (param.trim().contains(search))  // ----- Per vedere se una stringa si trova dentro un'altra stringa così posso vedere se ha trovato parametri dentro la string
			System.out.println(search + " trovato dentro la string");
		else
			System.out.println(search + " Non trovato dentro la string");
		if ("CORSO".equalsIgnoreCase(search)) //Serve anche in questo caso per vedere se c'è quella parola dentro search
			System.out.println(" parametro corso trovato");
		
		
		String [] array = new String  [4]; //array statico perché so definisco già la sua lunghezza
		array [0] = "primo";
		array [1] = "secondo";
		array [2] = "terzo";
		array [3] = "quarto";
		// array [4] = "quinto";  Mettendo pure questo elemento andrebbe in errore, perché avrei più di 5 elementi
		
		array[1] = "modificato";
		
		for (int idx = 0 ; idx < array.length ; idx++) { //.lenght dice qual è lunghezza array
			System.out.println("idx: " + idx + " valore: " + array[idx]);
		}
		
		for (String it:array) {  //-----un altro modo di scrivere for
			System.out.println(it);
		}
		
		int [] numeri = {10 , 20, 30, 40, 50}; //altro modo di inizializzare un array statico
		for (int it:numeri) //"Per ogni valore intero it contenuto nell'array numeri stampalo". Questo for non usa indici (i) e serve solo quando devo leggere semplicemente una serie di variabili
		System.out.println(it);
		
		// Wrapped Class (vedi in alto wrapped corrispondenti ai vari tipi
		
		Integer ii = null;  //se null lo scrivessi con int ii = null mi darebbe errore, ma non con la classe wrapped Integer
		
		Integer [] numeri1 = {10, null, 30, 40};
		for (Integer it:numeri1) //se avessi scritto int e non Integer, avrebbe dato errore
			System.out.println(it);
		
		String [] []  multi = new String [3] [4]; //Array multidimensionale, 2 dimensioni. Sono 3 righe e 4 colonne
		for (int id = 0; id < multi.length ; id++ ) {
			for (int jd = 0; jd < multi[id].length; jd++) {
				multi[id] [jd] = "prova_" +id + "_" +jd;
				System.out.println(" id: " + id + " jd: "+jd);
			}
			System.out.println("id 1 , jd 3:" + multi [1] [3]);
		}
		
		String [] [] [] multi3 = new String [4] [5] [6];
		for (int id = 0; id < multi3.length; id++) {
			for (int jd = 0; jd < multi3[id].length; jd++) {
				for (int xd = 0; xd < multi3[id] [jd].length; xd++) {
					multi3 [id] [jd] [xd] = "prova_" + id + "_" +jd + "_" + xd;
					System.out.println(" id: " + id + " jd: " + jd + " xd: " +xd);
				}
			}
		}
		System.out.println(" id 2 , jd 3, xd 4 " + multi3 [2] [3] [4] );
		// Array dinamiche (posso inserire o rimuovere elementi in qualsiasi momento)
		
		ArrayList<String> aL = new ArrayList<String>();  //Array di nome aL
		aL.add("Pietro");
		aL.add("Paolo");
		aL.add("Pink");
		aL.add("Pallino");
		
		System.out.println("Lenght: " + aL.size()); //mi dice lunghezza arraylist
		
		aL.add("Berta");  //così aggiungo elemento all'array
		
		for (String it:aL) {   
			System.out.println(it);
		}
		System.out.println("aL[3]:" + aL.get(3)); //così vedo qual è l'elemento specifico dell'array
		aL.remove(3); // mi permette di rimuovere un elemento
		
		for (String it:aL) {
			System.out.println(it);
		}
		
		//Chiamo Classe TestUser e creo oggetto usr di classe TestUser che ho creato separatamente
		
		User usr = new User("Pippo","Grande", false);
		
		System.out.println(usr.getNome()); //così stampo nome
		System.out.println(usr.toString()); //stampa indirizzo in memoria, mentre se nella classe TestUser ho generato il toString, vedo come sono valorizzati attributi
		
		usr.setCognome("Piccolo"); //cambio valore dell'attributo
		System.out.println(usr); //Stessa cosa di usare il toString
		
		ArrayList<User> lU = new ArrayList<User>(); //creerà un array di oggetti
		
		lU.add(new User ("Piero", "Ilgrande", true));
		lU.add(new User ("Paolo", "Rosso", true));
		lU.add(new User ("Anna", "Verde", false));
	//	lU.add(new User ("Gianluca", "Giallo", "M"));
		lU.add(new User ("Maria", "Nero", true));
	//	lU.add(new User ("Enrico", "Viola", "F"));
		
        //list fino a raggiungimento di un cognome
		
		search = "giallo";
		
		for (User it:lU) {     // così stampo la lista con i vari oggetti con attributi valorizzati
			System.out.println(it);
			// if (search.equalsIgnoreCase(it.getCognome());
			//    break;
		}
		/*
		 * Se voglio sostituire un elemento in un arrayList devo fare:
		 * nomeArrayList.set {indice, elemento};
		 * Es. ho un ArrayList<String> lista = new ArrayList<String>();
		 * e imposto elementi con .add
		 * Poi voglio modificare quello di indice 2:
		 * lista.set(2, "B").
		 */
						
			//Creo oggetto della classe PromozioneAutomatica
			//Faccio così un esempio di overloading: lo stesso metodo è usato in modi diversi
			
			PromozioneAutomatica pro = new PromozioneAutomatica();
			pro.method(10);
			pro.method(10);
			pro.method(2.0f);
			pro.method("aaaa");
			
			//Se ho messo metodi STATIC nella classe PromozioneAutomatica, posso anche scrivere adesso così NomeClasse.nomemetodo() , come vedo senza creare oggetto:
			
			PromozioneAutomatica.method(10);  //cioò mi consente di non dover creare oggetti per eseguire metodi
			PromozioneAutomatica.method(2.0f);
		}
		
		
	}
		
		

	

	
	
		

		
		
		

