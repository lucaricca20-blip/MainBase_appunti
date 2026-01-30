import java.util.Scanner;

import com.betacom.exceptions.AcademyException;
import com.betacom.process.ProcessAnonima;
import com.betacom.process.ProcessCollection;
import com.betacom.process.ProcessDate;
import com.betacom.process.ProcessEnum;
import com.betacom.process.ProcessExceptions;
import com.betacom.process.ProcessGenerics;
import com.betacom.process.ProcessInner;
import com.betacom.process.ProcessJson;
import com.betacom.process.ProcessReflection;
import com.betacom.process.ProcessSequential;
import com.betacom.process.ProcessSingleTone;
import com.betacom.process.ProcessStream;
import com.betacom.process.StringProcess;

import interfaces.ProcessInterface;

import java.util.HashMap;
import java.util.Map;

public class MainProcess { //vedi il pdf MainProcess.pdf per capirlo bene

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);  //Inizio a scrivere Sc poi clicco su ctrl+spazio e mi suggerisce classe Scanner, e mi fa l'import di java.util ecc
		//System.out.print("Introdurre un parametro [string,exception, date, enum, collection, singleton, sequential, anonima, stream, reflection, inner, generics, json]: "); date non ci interessa, è messo dopo
		//String inp = sc.nextLine();
		String inp = "generics"; //lo metto io di base, altrimenti riattivo lo scanner da tastiere delle due righe precedenti
		/*
		 * Nella mappa seguente metto come  chiave la stringa col nome del processo,
		 * e come valore il processo stesso (in ProcessInterface abbiamo execute,
		 * il metodo che poi abbiamo usato in modo diverso nelle varie classi che abbiamo
		 * fatto. Nella mappa quindi potrò mettere qualsiasi oggetto che implementa
		 * l'interfaccia ProcessInterface.
		 */
		
		Map<String,ProcessInterface> pr = new HashMap<String, ProcessInterface>();
		pr.put("string", new StringProcess()); //chiave e valore: aggiungo così elementi alla mappa
		pr.put("exception", new ProcessExceptions());
		pr.put("interface", new ProcessDate());
		pr.put("date", new ProcessDate());
		pr.put("enum", new ProcessEnum());
		pr.put("collection", new ProcessCollection()); //ProcessCollection (e anche le altre scritte prima) è una classe concreta, posso istanziarla
		pr.put("singletone", new ProcessSingleTone());
		pr.put("sequential", new ProcessSequential());
		pr.put("anonima", new ProcessAnonima());
		pr.put("stream", new ProcessStream());
		pr.put("reflection", new ProcessReflection());
		pr.put("inner", new ProcessInner());
		pr.put("generics", new ProcessGenerics());
		pr.put("json", new ProcessJson());
		
		/*
		 * Adesso gli dico che se l'elemento pr contiente come chiave (stringa) ciò
		 * che metto come inp (o, se tolgo i commenti, che inserisce l'utente come inp)
		 * allora prova a eseguirlo, altrimenti con i catch gestiamo le eccezioni (es.
		 * se utente inserisce nome di un processo che non esiste, voglio che il programma
		 * mi dica che quel processo non esiste).
		 */
		
		if (pr.containsKey(inp)) { 
			
			try { //se si verifica quanto detto sopra, prova il codice seguente
				
				/*
				 * Ricorda che parlando di mappe, il get ci permette di vedere il valore
				 * associato a una chiave. Allora se faccio pr.get(inp) gli sto dicendo
				 * che deve leggere il valore associato alla chiave di inp (es. se utente
				 * inserire come inp "collection", lui intanto prima di entrare nel try
				 * vede se collection è una chiave della mappa; poi dentro al try vede
				 * qual è il valore associato a collection (è un oggetto
				 * new ProcessCollection()) e lo assegna a ex. Poi chiama il metodo
				 * execute con ex, e questo metodo è contenuto in ProcessCollection, che è
				 * una classe che ha implementato l'interfaccia ProcessInterface.
				 * 
				 * ATTENTO: in ProcessInterface ex = pr.get(inp); non sta creando un nuovo
				 * oggetto, ma prende un oggetto che è già stato creato prima (nei
				 * pr.put, es. new ProcessCollection) e lo assegna a una variabile
				 * chiamata ex, che viene trattata come ProcessInterface, cioè gli interessa
				 * solo che quell'oggetto ha il metodo execute, che è nell'interfaccia
				 * ProcessInterface e che è stato implementato da tutte le altre (se 
				 * ProcessCollection, per es., ha un metodo in più, a java in questo
				 * momento non interessa.
			
				 */
				
				ProcessInterface ex = pr.get(inp);
				ex.execute();
				
				//Se trova Exception scritte nei catch, comportati come scritto
				//dentro la catch.
			}
		    catch (AcademyException e) { //se execute lancia un'AcademyException (indicata in quella classe),allora esegue questo catch
			System.out.println("Errore applicativo:" + e.getMessage()); // getMessage contiene la spiegazione dell'errore
			e.printStackTrace(); // è il dettaglio tecnico dell'errore
	        } catch (Exception e) { //Cattura qualsiasi errore perché riguarda la classe Exception, nativa in java, contiene tutti gli Exception
		    System.out.println("Abnormal end:" + e.getMessage());
		    e.printStackTrace();
		    }
			
			/*
			 * altrimenti, se inp non era una chiave della mappa (quindi non è un processo
			 * che abbiamo messo nella mappa, dici che non è previsto
			 */
		    } else {
			System.out.println("process non prevista");			
		}
		
		
		
		/*
		 * Adesso creerò una variabile pr di tipo ProcessInterface (non è un oggetto, ma
		 * solo una variabile vuota, infatti scrivo null). è un'interfaccia, quindi i suoi
		 * metodi devono essere implementati. Essa contiene metodo boolean execute (). Ciò
		 * vuol dire che chiunque implementa questa interfaccia deve avere il metodo
		 * execute (). Infatti, le classi ProcessExceptions e StringProcess contengono
		 * il metodo execute(), dato che implementano l'interfaccia.
		 * All'inizio quindi non so se l'utente scriverà string o exception, quindi 
		 * il fatto di creare una variabile ProcessInterface mi permette poi di assegnarle
		 * l'oggetto solo dopo che ho effettivamente creato l'oggetto, che è diverso in base
		 * al fatto che io scriva string o exception, come vedo sotto.
		 * Se non mettessi ProcessInterface pr = null, l'oggetto pr creato dentro lo 
		 * switch, rimarrebbe lì dentro e quindi poi non potrei eseguire l'execute
		 */
		
		/*
		 ProcessInterface pr = null;
		
		/*
		 * Con switch posso evitare di scrivere tanti IF. Tra parentesi scrivo inp che 
		 * è l'input da tastiera che ho scritto all'inizio. Se ho scritto string mi crea
		 * un nuovo oggetto pr di tipo StringProcess (vedi StringProcess.java), se scrivo
		 * exception mi crea un oggetto pr di tipo ProcessExceptions (vedi ProcessExceptions.java)
		 *, altrimenti si avvia il default che semplicemente ci avvisa che quella parola
		 *non è prevista e interrompe lo switch.
		 */
		/*
		switch (inp) {
		case "string": {
			pr = new StringProcess();
			break;
		}
		case "exception": {
			pr = new ProcessExceptions();
			break;
		} case "date": {
			pr = new ProcessDate();
			break;
		
		} case "enum": {
			pr = new ProcessEnum();
			break;
		}
		case "collection": {
			pr = new ProcessCollection();
			break;
		}
		default:
			System.out.println("process non prevista");
			System.exit(9); //senza questo, continuerebbe in sequenza e andrebbe in tilt. Metto 9 perché è il livello massimo di System exit
		}
		
		
		//Alternativa usando IF e non Switch
		// if ("string".equalsIgnoreCase(inp)) pr = new StringProcess ();
		// if ("exception".equalsIgnoreCase(inp)) pr = new ProcessExceptions ();
		
		
		/*
		 * A questo punto ho due scelte: o uso IF per eseguire execute come vedo in basso
		 * (l'ho commentato per non farlo eseguire), oppure col try/catch.
		 * Il TRY/CATCH serve appunto per dire al sistema che se si verifica una determinata
		 * eccezione (si indica proprio il codice che deve eseguire), la deve gestire in 
		 * un certo modo, che indico nel catch, così che compilando il programma possa
		 * proseguire e non bloccarsi a quell'errore, dato che viene subito gestito.
		 * Il secondo catch che ho messo è quello di sicurezza, cioè quello che prende
		 * tutti gli Exception che ci sono in java e verifica se sono presenti nel mio
		 * codice e me lo fa sapere.
		 */
		/*
		try {
			pr.execute();
			System.out.println("Normal end"); //Questa riga viene eseguita solo se execute non riporta errori
		} catch (AcademyException e) { //se execute lancia un'AcademyException (indicata in quella classe),allora esegue questo catch
			System.out.println("Errore applicativo:" + e.getMessage()); // getMessage contiene la spiegazione dell'errore
			e.printStackTrace(); // è il dettaglio tecnico dell'errore
	    } catch (Exception e) { //Cattura qualsiasi errore perché riguarda la classe Exception, nativa in java, contiene tutti gli Exception
		    System.out.println("Abnormal end:" + e.getMessage());
		    e.printStackTrace();
		}
		*/
		/*Posso provare il funzionamento: vado in ProcessExceptions e metto p2 = 0, oppure
		 * sempre in ProcessException non valorizzo uno dei tre attributi con SET.
		 */
		
		
		/*
		 * Adesso voglio eseguire il metodo execute. Grazie al polimorfismo, il metodo sarà
		 * diverso se avrò scritto string o exceptions. Entrambe hanno il metodo execute
		 * perché implementano l'interfaccia ProcessInterface, ma poi lo implementano
		 * in modo diverso. In ogni caso, se il metodo execute viene avviato, alla fine
		 * voglio che mi restituisca Normal end se alla fine del metodo c'è return true
		 * altrimenti abnormal end.
		 */
	//	if (pr.execute()) //IL METODO EXECUTE LO TROVI IN StringProcess.java. IF verrà eseguito se execute fornirà true (è un boolean)
	//		System.out.println("Normal end");
	//	else
	//		System.out.println("Abnormal end");

	}

}


