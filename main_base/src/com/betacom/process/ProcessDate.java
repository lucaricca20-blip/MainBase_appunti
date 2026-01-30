package com.betacom.process;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.betacom.objects.User;
import com.betacom.utils.Utilities;

import interfaces.ProcessInterface;

public class ProcessDate implements ProcessInterface {
	
	private final static String PATTERN_DATE = "dd/MM/yyyy";
	
	private final static String PATTERN_DATE_ESTESO = "EEEE dd MMMM yyyy HH:mm:ss"; //per capire pattern da mettere vai su https://docs.fact-finder.com/docs/basic-settings/fields/date-format-patterns

	private final static String PATTERN_DATE_ESTESO_GIORNO = "EEEE dd MMMM yyyy";
	
	private final static String PATTERN_DATE_ESTESO_ORA = "HH:mm:ss";
	
	@Override
	public boolean execute() throws Exception {
		System.out.println("Begin ProcessDate");
		
		LocalDateTime adesso = LocalDateTime.now(); //LocalDateTime (LocalDate invece va bene solo per la data, non per orario) è nella JVN, basta chiamarlo e importarlo
		
		/*
		 * Usiamo il metodo dateToString della classe Utilities per convertire la data attuale
		 * in una stringa (per poterci lavorare) e la assegno ad r. Ricorda che .format è un
		 * metodo della classe String che ci permette di lasciare dei "buchi" (come %s, se è una
		 * stringa) e di riempirli con qualcosa che scriviamo dopo le virgolette e la virgola) che
		 * in questo caso riempio con la data trasformata in stringa. Posso usare il metodo 
		 * dateToString direttamente su Utilities perchè è un metodo statico, posso quindi
		 * non usarlo su un oggetto.
		 */
		String r =String.format("Adesso siamo il %s", Utilities.dateToString(PATTERN_DATE, adesso));
		System.out.println(r);
		
		/*
		 * Faccio adesso la stessa cosa di prima ma adesso uso il pattern più esteso, che contiene
		 * pure l'ora
		 */
		
		r = String.format("Adesso siamo il %s ora: %s", Utilities.dateToString(PATTERN_DATE_ESTESO_GIORNO, adesso),
		                             Utilities.dateToString(PATTERN_DATE_ESTESO_ORA, adesso));
		System.out.println(r);
			                          
			
		/*
		 * Date in User
		 */
		
		LocalDate u = LocalDate.parse("1970-10-05"); // Trasformo una stringa scritta tra parentesi e la converto in oggetto LocalDate
		
		
		User usr = new User ("Marco", "Verde", "M", u); //il costruttore di usr vuole 3 stringhe e un oggetto LocalDate
		System.out.println(usr); //posso stampare tutti gli attributi e vedere come sono valorizzati perché in User ho generato toString
		
		usr = new User ("Anna", "Bianco", true , 2000, 5, 13); //ho messo pure il costruttore che mi consente di inserire anno mese giorno come parametri
		
		/*
		 * Adesso voglio stampare prima la data di nascita di Anna Bianco, con il primo Syso
		 * usando il metodo dateToString che mi converte la data in Stringa, e poi col
		 * secondo Syso voglio stampare tutti gli attributi con i loro valori di usr
		 */
		System.out.println("Data nascita:" + Utilities.dateToString(PATTERN_DATE, usr.getDataNascita()));
		System.out.println(usr);
		
		/*
		 * Voglio adesso aggiungere 25 giorni alla data di nascita iniziale e per farlo
		 * imposto la data di nascita con SET e gli dico che la deve impostare leggendola
		 * prima con GET e poi aggiungendo i giorni della variabile plusGiorni con il 
		 * metodo plusDays. Con il Syso successivo stampo la data modidicata usando il metodo
		 * dateToString per convertire la data che legge (con GET) (usando il formato
		 * pattern_date) in stringa.
		 */
		
		int plusGiorni = 25;	
		usr.setDataNascita(usr.getDataNascita().plusDays(plusGiorni));
		
		System.out.println("Data nascita modificata:" + Utilities.dateToString(PATTERN_DATE, usr.getDataNascita()));
		
		/*
		 * Adesso valorizzo DataCertificatoMedico con SET, e gli imposto la data
		 * assegnata a dateParam, convertendola in LocalDate con il metodo stringToDate.
		 * Valorizzo poi MesediValidita. Infine stampo gli attributi e i loro valori
		 */
		
		String dateParam = "21/01/2025";
		
		usr.setDataCertificatoMedico(Utilities.stringToDate(PATTERN_DATE, dateParam));
		LocalDate myDate = Utilities.stringToDate(PATTERN_DATE, dateParam);
		usr.setMeseDiValidita(12);
		System.out.println(usr);
		
		/*
		 * controllo validità certificato medico
		 * Intanto faccio leggere qual è la DataCertificatoMedico e gli faccio aggiungere i
		 * mesi letti con GET da MeseDiValidità, usando metodo plusMonths. Poi converto
		 * la data (LocalDate) in stringa per stamparla.
		 * 
		 * Poi gli dico che se la data odierna è successiva alla data endDate, allora mi
		 * deve dire che il certificato è scaduto e mi deve dire in che data (con GET);
		 * altrimenti mi dice che è valido e riporta la data.
		 */
		
		LocalDate endDate = usr.getDataCertificatoMedico().plusMonths(usr.getMeseDiValidita());
		System.out.println(Utilities.dateToString(PATTERN_DATE, endDate));
		
		if(LocalDate.now().isAfter(endDate))
			System.out.println("certificato medico scaduto " + usr.getDataCertificatoMedico());
		else 
			System.out.println("certificato medico valido " + usr.getDataCertificatoMedico());
		
		return false;
	}

}

