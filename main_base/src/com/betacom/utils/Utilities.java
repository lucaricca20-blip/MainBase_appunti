package com.betacom.utils;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

import com.betacom.exceptions.AcademyException;


public class Utilities {
	
	/* metodo dateToString serve a trasformare una data in una stringa (restituisce una stringa)
	 * Vuole in ingresso una stringa (che abbiamo chiamato pattern) e una data (quindi di
	 * tipo LocalDate) che abbiamo cambiato myDate. Il pattern serve a fargli capire in che
	 * formato indichiamo la data. A COSA CI SERVE dateToString? A poter stampare le date (oggetti di tipo LocalDate),
	 * che altrimenti non potrei stamparle.
	 * 
	 */
	
	public static String dateToString (String pattern, LocalDate myDate) { 
		
		/*
		 * Adesso devo dire a Java con che formato voglio mettere la data (per esempio
		 * dd/MM/yyy) e per fare ciò devo usare un formartter, che è il seguente.
		 * Il formatter legge pattern e poi Locale.ITALIAN trasforma le parole inglesi
		 * in italiano (DataTimeFormatter è una classe di Java, e io creo un oggetto
		 * di quella classe chiamandolo formatter (ofPattern è un costruttore privato
		 * di DataTimeFormatter)
		 */
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern, Locale.ITALIAN);
		
		/*
		 * Infine il metodo prende la data myDate e la trasforma in una stringa usando
		 * il formatter.
		 */
		
		return myDate.format(formatter);
	}
	
	/*
	 * Di seguito riscrivo questo metodo ma adesso uso LocalDateTime perché oltre la data
	 * voglio pure l'orario
	 */
	
	public static String dateToString (String pattern, LocalDateTime myDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern, Locale.ITALIAN);
		
		return myDate.format(formatter);
	}
	
	/*
	 * Adesso creo metodo stringToDate, che ritorna una data (infatti dopo static stavolta
	 * leggo LocalDate). Anche in questo caso prende in ingresso una stringa con il pattern
	 * della data e la data stessa sotto forma di testo. C'è pure il throws perché così
	 * inseriamo un'exception nel caso di data formato non valida e così ci avvisa. In 
	 * questo caso voglio che la stringa della data diventi un oggetto (l'oggetto LocalDate r)
	 *  A COSA CI SERVE stringToDate? Ci serve per passare da stringa a LocalDate (oggetto di tipo LocalDate)
	 *  quindi da stringa a data. Se non facessi questo, non potrei fare operazioni con le
	 *  date, come aggiungere giorni (.plusDays, vedi ProcessDate.java), mesi ecc.
	 *  Spesso da database ecc arrivano date in stringa ma dobbiamo modificarle per poterci
	 *  lavorare
	 */
   public static LocalDate stringToDate(String pattern, String myDate) throws AcademyException {
	   LocalDate r = null; //inizializzo l'oggetto r (tipo LocalDate) con null, mi serve dopo
	   
	   try {
		   //Anche in questo caso creo il formatter per comunicare il formato a Java
	   DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern, Locale.ITALIAN);
	   r = LocalDate.parse(myDate, formatter); //fa la conversione della stringa myDate in oggetto LocalDate e lo assegna ad r
	  
	   /* Se si verifica DataTimeParseException, Java cattura l'errore e lo gestisce come
	    * AcademyException: quindi fa comparire "Formato della data invalida" seguita
	    * dalla data stessa.
	    * 
	    */
	   
	   } catch (DateTimeParseException e) { 
		   throw new AcademyException("Formato della data invalida:" +myDate);
	   }
	   
	   
	   return r;
   }
	public static LocalDate dateToLocalDate(Object value) {
		if (value == null) return null;
		return ((Date)value).toLocalDate();
	}


}