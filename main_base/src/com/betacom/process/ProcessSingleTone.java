package com.betacom.process;

import com.betacom.other.CallSingletone;
import com.betacom.singletone.MySingletone;

import interfaces.ProcessInterface;

public class ProcessSingleTone implements ProcessInterface {

	@Override
	public boolean execute() throws Exception {
		System.out.println("Begin ProcessSingleTone");
		/*
		 * prima vedi classi MySingletone e CallSingletone
		 * Attento: la seguente variabile idx è locale, non è quella del singletone
		 * anche se ha lo stesso nome. Mi serve per fare in modo che ad essa venga
		 * assegnato il nuovo valore di idx dopo che chiamo il metodo computeIndice.
		 */
		Integer idx = MySingletone.getInstance().computeIndice(); //getInstance crea nuova instance (ancora non ce n'è), mentre computeIndice aumenta di 1 idx
		
		System.out.println("Valore indice:" +idx); //stamperà Valore indice: 1
		
		/*
		 * Adesso provo a eseguire il metodo test della classe CallSingletone. Come vedi
		 * ha creato un oggetto temporaneo della classe CallSingletone (è temporaneo perché
		 * non ha scritto CallSingletone c = new CallSingletone(); c.test(); , quindi non
		 * lo ha assegnato a nessuna variabile (come per es. c). Ci serve solo per fare 
		 * aumentare l'indice idx con oggetto di classe CallSingletone, per dimostrare
		 * che pure oggetti di classi e package diversi possono intervenire sullo stesso
		 * oggetto grazie al singleton.
		 */
		new CallSingletone().test(); //l'indice va a 2, quindi interviene su stesso oggetto di prima (metodo test chiama pure getInstance, ma stavolta ritorna direttamente instance, è già creata, non è più null
		
		/*
		 * Adesso per una terza volta i due metodi vengono chiamati, stavolta dentro al
		 * Syso. Dato che è sempre lo stesso singleton, idx aumenta a 3. Se non avessi
		 * usato il singletone, idx sarebbe rimasto 1 in una classe e 1 nell'altra, mentre
		 * così ho potuto agire sullo stesso partendo da classi diverse.
		 */
		
		System.out.println("Valore indice chiamato di nuovo: " +MySingletone.getInstance().computeIndice());;
		return false;
	}
	

}
