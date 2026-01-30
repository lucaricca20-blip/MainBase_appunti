package com.betacom.process;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.betacom.objects.GenericResponse;
import com.betacom.objects.Impiegato;
import com.betacom.objects.ImpiegatoStream;
import com.betacom.objects.User;

import interfaces.ProcessInterface;

public class ProcessGenerics implements ProcessInterface{
	
	/*
	 * Vedi prima Classe GenericResponse.

	 *  Creo una lista riempiendola di oggetti impiegato grazie a uno stream.
	 *  
	 */

	@Override
	public boolean execute() throws Exception {
		System.out.println("Begin ProcessGenerics");
		
		List<Impiegato> lI = Stream.of(
				new Impiegato("Paolo", "Rossi", true, 2000.00),
				new Impiegato("Pietro", "Verde", true, 1300.00),
				new Impiegato("Anna", "Giallo", false, 1500.00),
				new Impiegato("Giuseppe", "Nero", true, 1800.00),
				new Impiegato("Alex", "Bianco", true, 1400.00),
				new Impiegato("Cristina", "Griggio", false, 1600.00),
				new Impiegato("Ugo", "Blu", true, 1200.00),
				new Impiegato("Maria", "Pastel", false, 1900.00)
				).collect(Collectors.toList());
		
		/*
		 * Creo un oggetto di nome "resp" di classe GenericResponse: T è Impiegato e U è String,
		 * adesso abbiamo popolato i tipi generici, gli abbiamo detto quali tipi
		 * effettivamente devono essere.
		 * Posso usare il set per valorizzare gli attributi, dato che sono nella classe GenericResponse.
		 * L'attributo "data" lo valorizzo con la lista lI che contiene gli oggetti Impiegato creati
		 * e valorizzati prima grazie allo stream. GenericResponse ha il toString, quindi poi posso stampare
		 * resp e mi darà tutti gli attributi valorizzati.
		 * 
		 */
		
		GenericResponse<Impiegato, String> resp = new GenericResponse<Impiegato, String>();
		resp.setRc(true);
		resp.setMsg("Operazione eseguita");
		resp.setData(lI);
		
		System.out.println(resp);
		
		/*
		 * Attento: adesso creo una list di oggetti User utilizzando List.of : la differenza tra il creare una List così
		 * oppure sfruttando lo stream.of è che se la creo con List.of non potrò poi modificarla (fare add o remove), perché
		 * in run time mi darebbe errore. Quindi con List.of la lista diventa immutabile.
		 * Per risolvere potrei scrivere List <User> lU = new ArrayList<User>(List.of(
		 * 
		 *  ));
		 *  
		 *  In questo modo anche se uso List.of, tratta la lista come ArrayList e quindi è modificabile.
		 *  
		 *  Il vantaggio della list.of (immutabile) è che è più veloce in run time, ma lo svantaggio è che è immutabile appunto.
		 */
		
		List<User> lU = List.of(
				new User("Paolo", "Berat", "M", 2000, 2, 6),
				new User("Anna", "Bella", "F", 2006, 5, 9),
				new User("Gianni", "Laverdura", "M", 1998, 8, 14),
				new User("Maria", "Angela", "F", 2001, 5, 1)				
				);
		
		
		/*
		 * Creiamo un oggetto di tipo ImpiegatoStream, e nel costruttore mettiamo nome e cognome. Creo un oggetto GenericResponse:
		 * stavolta T sarà User e U ImpiegatoStream. Col set infatti aggiungiamo l'oggetto imS e la lista lU.
		 * Poi stampa: abbiamo il toString quindi ci stamperà sia le info di imS che di lU, dato che sono in resp1.
		 */
		
		ImpiegatoStream imS = new ImpiegatoStream("tes1", "test2");
		
		GenericResponse<User, ImpiegatoStream> resp1 = new GenericResponse<User, ImpiegatoStream>();
		resp1.setRc(true);
		resp1.setMsg(imS);
		resp1.setData(lU);
		
		System.out.println(resp1);
		
		return false;
	}

}