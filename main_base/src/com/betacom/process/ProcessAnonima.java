package com.betacom.process;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import com.betacom.objects.Impiegato;

import interfaces.Azione;
import interfaces.ProcessInterface;

public class ProcessAnonima implements ProcessInterface {

	@Override
	public boolean execute() throws Exception {
		System.out.println("Begin ProcessAnonima");
		
		/*
		 * Vogliamo una classe anonima che implementa un'interfaccia. In questo
		 * caso implementerà l'interfaccia "Azione". Lez del 28.01.26
		 * A volte è inutile creare classi nuove se poi le usiamo per poche
		 * righe di codice ( in questo caso solo per poi fare a.esegui, in
		 * cui esegui è un metodo dell'interfaccia Azione). Allora dentro a 
		 * una classe (ProcessAnonima) si crea una classe anonima, 
		 * solitamente caratterizzata da un new di tipo interfaccia (new Azione; che
		 * in genere non si può fare perché le interfacce non si istanziano), e
		 * poi si aprono delle parentesi graffe, all'interno delle quali
		 * si riempie questa classe senza nome. Dentro la classe anonima
		 * facciamo l'override dell'interfaccia Azione e mettiamo, in questo
		 * caso, un Syso.
		 * Fuori dalla classe anonima possiamo eseguire il metodo esegui
		 * sull'oggetto a.
		 */
		
		Azione a = new Azione () { //in genere non posso creare oggetti di tipo interfaccia
			/*
			 * Senza classe anonima avrei dovuto fare una classe che 
			 * implementasse un metodo. Ma non serve fare il new Azione,
			 * esso non esiste, e infatti nella lambda che metto dopo non ci
			 * sarà.
			 */
	

			@Override
			public void esegui(String param) {
				System.out.println("Azione eseguita con parametro " + param);
				
			}

			
		};
		a.esegui("mio parametro");
		
		/*
		 * Trasformazione della classe anonima in Lambda, così codice è più
		 * conciso. 
		 * Possiamo però risparmiare codice se usiamo la funzione Lambda invece
		 * della classe anonima, e arrivare allo stesso risultato.
		 * La lambda ci permette di scrivere un oggetto di un'interfaccia
		 * funzionale (interfaccia che ha un solo metodo astratto, come
		 * Azione che ha solo esegui).
		 * Come in basso abbiamo creato un metodo "metodo" che prende in 
		 * ingresso una stringa.
		 * Dove scrivo:
		 * 		Azione l = (param) -> metodo(param);
		
		 *      l.esegui(" parametro");
		 *è come se scrivessi una classe anonima:
		 *  Azione l = new Azione() {
            @Override
              public void esegui(String param) {
              metodo(param);
              }
            };

		 * La riga Azione l = (param) -> metodo(param); è come se dicesse:
		 * 
		 * “Crea un oggetto Azione il cui metodo esegui prende param e chiama metodo(param)”.
		 * In questa riga vedo che vengono date in ingresso al metodo "metodo"
		 * la stringa param, e dopo il metodo viene chiamato su oggetto l.
		 * Il primo (param) è il param del metodo esegui (è come scrivere 
		 * void esegui (String param) {...} (come con classe anonima.
		 * Il secondo (param) è come cosa deve fare con quel param, cioè la 
		 * parte del metodo che ci dice cosa deve fare con quel metodo.
		 * Immagina di avere:
		 * 
		 * public void esegui(String param) {
                     metodo(param);
           }

		 * Il primo param dice cosa serve in ingresso (stringa), il secondo
		 * cosa bisogna fare con quel parametro ricevuto (in questo caso darlo
		 * in ingresso a un altro metodo, chiamato "metodo"). Nella lambda
		 * queste due cose vengono riassunte in una riga.
		 * 
		 * In ProcessStream vedo:
		 * lI.forEach(Imp -> System.out.println(Imp));
		 * 
		 * Concentriamoci sulla lamda. è come se ci fosse scritto:
		 * 
		 * new Consumer<Impiegato>() { //(Consumer è interfaccia funzionale (con un solo metodo:accept) nativa, non perderci tempo
             @Override
             public void accept(Impiegato Imp) { //Solo questo è il metodo dell'interfaccia funzionale che prendiamo in esame
             System.out.println(Imp);
            }
           }
           
           Ovvero è come se ci fosse un metodo che prende in ingresso un oggetto
           Impiegato (metodo accept) e stampa Imp stesso (Imp nella sua classe
           ha il toString, quindi facendo così stampa attributi e loro valori).
           Il lI.forEach dice semplicemente di eseguire l'operazione contenuta nelle
           parentesi per tutti gli elementi della list lI.
           

		 * 
		 * Appunti scritti durante la lezione:
		 * Nella riga seguente è ripotata la stessa cosa di prima
		 * ma pi+ù conciso con lambda. è una sola azione, non servono le 
		 * graffe in questo caso. Posso farlo solo se implento un'interfaccia
		 * funzionale, cioè un'interfaccia che ha un solo metodo (esegui()). Se 
		 * metto più istruzioni nella lambda (es. più Syso) ci vogliono le graffe.
		 * Param adesso si porta in ingresso al metodo.
		 * Dopo la freccia metto l'implementazione dell'unico metodo che ho.
		 * Non prendo il metodo "metodo" dall'interfaccia, ma è in questa classe
		 * stessa.
		 * Se lambda si usano molto negli stream. Si deve evitare di scrivere
		 * molto nelle lambda e di richiamare altre lambda nelle lambda.
		 * 
		 */
		
		Azione l = (param) -> metodo(param);
		
		l.esegui(" parametro");
		
		
		
		
		
		
		
		/*
		 * Se da ProcessStream prendo:
		 * 		List<Impiegato> lI = Stream.of( //Poi voglio trasformare lo stream in list, altrimenti prima di <Impiegato> avrei potuto scrivere Stream
				new Impiegato("Paolo","Verdi", true, 1800.0), //ricorda costruttore di Impiegato. Lo puoi richiamare con ctrl+spazio e te li suggerisce
				new Impiegato("Pietro","Bianchi", true, 1900.0),
				new Impiegato("Anna","Rossi", false, 2000.0),
				new Impiegato("Giuseppe","Giallo", true, 2100.0),
				new Impiegato("Alex","Viola", true, 2200.0),
				new Impiegato("Cristina","Pretty", false, 2300.0),
				new Impiegato("Ugo","Marrone", true, 2400.0),
				new Impiegato("Francesco","Blue", true, 2500.0)
				) // ma poi cambio:
				.collect(Collectors.toList()); //con .toList() recupero una lista, ma questa non è più modificabile; con Collectors creiamo una List normale, quindi modificabile
				
			E da ProcessCollection prendo:
				private void sortBySalary (List<Impiegato> lI) { //Posso metterlo anche come metodo per fare confronti
		          //  lI.sort(new Comparator<Impiegato> () {
		        	//@Override
			       // public int compare(Impiegato o1, Impiegato o2) { //confronta due diversi oggetti impiegato in ingresso
				
				//    return Double.compare(o2.getSalary(), o1.getSalary()); // confronta salari per ordinarli
		//	}
		//  });
		
		E poi scrivo:
		lI.sort((Impiegato o1, Impiegato o2)) -> Double.compare(o2.getSalary(), o1.getSalary()));
		lI.forEach (i -> System.out.println(i));
		
		Ottieniamo la list con il sort effettuato. lI.sort sfrutta un'interfaccia
		funzionale, quindi posso ottimizzando così il codice.
		
		
		
		 */
		
		return false;
	}
	
	private void metodo (String param) {
		System.out.println("Azione eseguita con parametro" + param);
        System.out.println("Seconda istruzione");
	}

}
