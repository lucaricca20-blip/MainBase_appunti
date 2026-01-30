package com.betacom.process;

import java.lang.module.ModuleDescriptor.Builder;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.betacom.objects.Impiegato;
import com.betacom.objects.ImpiegatoStream;

import interfaces.ProcessInterface;

public class ProcessStream implements ProcessInterface {

	@Override
	public boolean execute() throws Exception {
		System.out.println("Begin ProcessStream");
		/*
		 * Lo stream è un insieme di dati (ma non è una collezione), il vantaggio
		 * è la comodità della loro manipolazione. Vediamo alcuni esempi di cosa
		 * si può fare. Usiamo la classe Impiegato fatta in precedenza.
		 * //Devo importare stream da java.util
		 * 
		 * Nel modo seguente utilizzo uno stream per creare velocemente
		 * una lista. Raggiungo lo stesso risultato ma così evito di fare
		 * molti .add. Creo allora una list con stream: è un modo più moderno
		 * di lavorare con java, ma risultato finale è uguale, solo che ci si
		 * arriva in modo più comodo.
		 */
		
		List<Impiegato> lI = Stream.of( //Poi voglio trasformare lo stream in list, altrimenti prima di <Impiegato> avrei potuto scrivere Stream. Dopo che sarà finito lo Stream.of e dopo il .toList(), diventerà una list, e quindi verrà a quel punto assegnata a sinistra (cioè a List <Impiegato> lI)
				
				new Impiegato("Paolo","Verdi", true, 1800.0), //ricorda costruttore di Impiegato. Lo puoi richiamare con ctrl+spazio e te li suggerisce
				new Impiegato("Pietro","Bianchi", true, 1900.0),
				new Impiegato("Anna","Rossi", false, 2000.0),
				new Impiegato("Giuseppe","Giallo", true, 2100.0),
				new Impiegato("Alex","Viola", true, 2200.0),
				new Impiegato("Cristina","Pretty", false, 2300.0),
				new Impiegato("Ugo","Marrone", true, 2400.0),
				new Impiegato("Francesco","Blue", true, 2500.0)
				)
				.toList(); //da stream a lista
				/*
				 * Potevo anche scrivere così:
				 * ).collect(Collectors.toList()); //da stream a lista
				 */
		
		/*
		 * La lista così implementa anche alcuni metodi. Fino ad adesso per
		 * stampare la lista facevamo il for. L'interfaccia List ha messo
		 * a disposizione il forEach, che è più comodo. Così faccio una
		 * lamda ispezione.
		 * 
		 * 		 * lI.forEach(Imp -> System.out.println(Imp));
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
		 */
		
		lI.forEach(Imp -> System.out.println(Imp)); //stampa gli impiegati che ho scritto sopra.
		
		/*
		 * Una Stream posso crearla con il builder
		 * 
		 * Utilizza il pattern builder (che vedremo più avanti). 
		 * Ho uno stream e voglio che faccia il builder sulle stringhe.
		 */
		
		Stream<String> streamBuilder = Stream.<String> builder()
				.add("lunedì")
				.add("martedì")
				.add("mercoledì")
				.add("giovedì")
				.add("venerdì")
				.add("sabato")
				.add("domenica")
				.build(); 
		
		// System.out.println("streamBuilder lenght: " + streamBuilder.count()); //numero di stringhe
		//Non posso tenere attivo il count perché non si possono fare più
		//operazioni sullo stesso builder, e tra qualche riga devo usarlo
		//per fare il toArray
		/*
		 * Voglio trasformare questo stream in un array di stringhe. 
		 * Si fa con un metodo dello stream, il .toArray. Ma cosa c'è
		 * dentro il toArray? Una funzione lambda così scritta:
		 * (size -> new String[size])
		 * 
		 * è come se avessi scritto un metodo come questo (la lambda però
		 * è anonima):
		 * public String [] generaArray (int size) {
		 * return new String [size];
		 * }
		 * 
		 * Il size è già previsto da .toArray, quindi Java sa già che in quel
		 * size deve mettere il numero di elementi che devono popolare l'array
		 * (vede quanti ce ne sono nello stream).
		 */
		
		String [] giorni = streamBuilder.toArray(size -> new String[size]);
		
		/*
		 * Adesso stampo la lunghezza dell'array e il giorno al quarto posto dell'indice (venerdì)
		 */
		System.out.println("giorni lenght: " + giorni.length + "giorni[4]: " + giorni[4]);
		
		/*
		 * Creazione random
		 * 
		 * 
		 * nextInt genera 10 numeri random
		 */
		
		Random ran = new Random (); //Un oggetto della classe Random permette di generare numeri casuali
		
		/*
		 * Stream.generate consente di creare uno stream con un valore
		 * inizialmente indefinito di valori filtro. Con la lambda uso
		 * la funzione nextInt sull'oggetto ran mettendo .limit(10) per indicare
		 * che deve generare massimo 10 valori.
		 * Al posto della lambda avrei potuto scrivere per es.
		 * 
		 * public static int generaCasuale () {
		 *   return new Random().nextInt;
		 * }
		 */
		
		Stream<Integer> sR = Stream.generate( () -> ran.nextInt() ).limit(10); //Avrei potuto mettere anche Long invece di Integer, e poi nextLong(), per numeri più grandi
		sR.forEach(n -> System.out.println(n)); //Stampa 10 num random
		
		/*
		 * Generazione dati primitive. Vogliamo che generi automaticamente
		 * un range di numeri. Usiamo oggetto di tipo IntStream, che è una
		 * versione specializzata di Stream per numeri interi.
		 */
		
		System.out.println("Generazione interi con range");
		IntStream intStream = IntStream.range(3, 21); //Il primo numero dice da quale numero deve cominciare, il secondo dice prima di quale numero deve fermarsi.
		
		/*
		 * Dobbiamo stampare il range. La funzione lambda rappresentata
		 * è come se dicesse:
		 * 
		 * public static void stampaRange (int i) {
		 *    System.out.println(i));
		 *}
		 *
		 *Posso anche sintetizzarla così: ti do in ingresso un int i e mi
		 *restituisci la stampa di i.
		 *Quindi questa stampa deve essere ripetuta per ogni i, cioè per
		 *ogni elemento dell'IntStream.
		 */
		intStream.forEach (i -> System.out.println(i));
		
		/*
		 * Adesso voglio fare un filtro. Es. ho una lista e voglio filtrarla.
		 * Il filtro voglio farlo usando Stream.
		 * Lo Stream lavora in sequenziale: esegue prima la prima funzione,
		 * poi la seconda ecc.
		 * 
		 * Funzione filter
		 */
		
		lI.stream() //Trasforma la list lI (dove abbiamo messo impiegati) in stream
		     .filter(im -> im.getSesso())  //prende solo maschi, perché in Sesso, maschio è true
		     .filter(im -> im.getSalary() >= 2000.00) //impiegati con salario superiore a 2000.00
		     .forEach(im -> System.out.println(im)); //stampa gli elementi filtrati con i due filtri
		   
		
		/*
		 * La prima lambda posso sostituirla così: ti do un oggetto im
		 * (classe Impiegato) e mi restituisci getSesso (siccome è un boolean, in questo caso gli sto dicendo che mi deve filtrare per true)
		 * 
		 */

		     
		     /*
		      * Funzione Map: per trasformare solo gli oggetti filtrati in elementi della List ImpiegatoStream.
		      * [ATTENTO: qui non c'entrano niente le Map con chiave e valore,
		      * .map è un metodo per trasformare degli elementi].
		      *  Uso pure filter:
		      * le due funzioni, .map e .filter, si possono accumulare.
		      * ALl'inizio faccio lI.stream che trasforma la lista lI in Stream (lo Stream mi servirà solo temporaneamente per manipolare i dati al fine di portarli nella List; lo Stream poi scomparirà
		      * ma sulla sinistra scrivo già List<ImpiegatoStream> perché lì
		      * dentro metterò la List.
		      * Dopo aver trasformato lista in String faccio filtro in base al
		      * sesso, in base al salario, e poi con .map creo degli oggetti
		      * ImpiegatoStream (con nome e cognome come attributi, come vedo
		      * nel costruttore della classe ImpiegatoStream) da mettere
		      * dentro alla nuova List usando infine il toList() e li assegna
		      * alla sinistra della prima riga (List <ListImpiegatoStream>), e la lista è creata.
		      * Alla fine stampo tutti gli elementi della nuova list lIs.
		      * 
		      * 
		      * Il forEach genera automaticamente una classe anonima, non è
		      * possibile fare break. Il return fa uscire dalla classe anonima
		      * e non dal forEach. Non tutti i for possono essere cambiati con
		      * forEach,
		      * 
		      * 
		      */
		System.out.println("map");
		
		List<ImpiegatoStream> lIs = lI.stream()
			     .filter(im -> im.getSesso())  //prende solo maschi, perché in Sesso, maschio è true
			     .filter(im -> im.getSalary() >= 2000.00) //impiegati con salario superiore a 2000.00
				 .map(imp -> new ImpiegatoStream(imp.getNome(), imp.getCognome()))
				.toList();
		lIs.forEach(i -> System.out.println(i));
		
		/*
		 * Count: prima passo da lista a stream e grazie ad esso faccio un filtro prendendo solo maschi, poi faccio
		 * .count che mi conta quanti elementi ha filtrato e assegna tale numero di tipo long a long count.
		 */
		System.out.println("Count");
		
		long count = lI.stream()
				.filter(im -> im.getSesso())
				.count(); //conta elementi dello stream dopo aver filtrato e restituisce un numero di tipo long.
		System.out.println("Numero di maschi: " + count);
		
		//Attento: lo stream scompare dopo l'ultima funzione (in questo caso .count)
		/*
		 * match:.anymatch restituisce un boolean (true o false in base al
		 * fatto che ciò che è indicato tra le sue parentesi sia vero o falso.
		 * IN questo caso verifica se esiste almeno un maschio il cui nome
		 * inizia con Z. è false, quindi lo assegna al boolean rc e poi facciamo
		 * la stampa. Esiste anche .allMatch che invece ritorna true solo
		 * se tutti gli elementi che scorre nello stream rispettano quella
		 * condizione.
		 */
		boolean rc = lI.stream() //lo trasforma in stream
				.filter(im -> im.getSesso())
				//potrei mettere .anyMatch(im -> !im.getSalary() >=2500); femmine (infatti !im, che è il false delle femmine) che hanno stipendio almeno 2500
				.anyMatch(im -> im.getNome().startsWith("Z")); //alcuni devono avere, quindi stamperà false perché nessuno inizia con Z
		System.out.println("result del match: " +rc);
	
		
		return false;
	}
// Vedi pure che uso ho fatto dello stream nell'ultima parte (che è messa come
// commento) di ProcessAnonima.
}
