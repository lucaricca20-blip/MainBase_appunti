package com.betacom.process;

import com.betacom.exceptions.AcademyException;
import com.betacom.objects.User;

public class ValidationUser {
	
	/*
	 * Con throws dico al sistema che sto per mettere delle exceptions, inserendole nella
	 * classe AcademyException, la quale prevede che queste vengano inviate alla classe
	 * nativa principale Exception, così che in MainProcess io possa vedere qual è l'exception
	 * con get.Message().
	 * Nel metodo checkUser, che vuole in ingresso un oggetto della classe User (che ha
	 * come attributi nome, cognome, sesso) poi inserisco tre exceptions con THROW NEW
	 * che indicano che se i tre attributi per quell'oggetto User non sono stati compilati
	 * il sistema deve restituirmi un'exception col messaggio ".... non caricato".
	 * In main process, con try/catch ho stabilito come devono essere gestite queste
	 * exception (cioè col get.Message, cioè inviandomi un messaggio che mi avvisa di quali
	 * eccezioni sono nel codice che effettivamente eseguo).
	 */
	
	public void checkUser (User usr) throws AcademyException { //richiamo classe User dal package objects. Con throws dico al compilatore che il metodo potrebbe lanciare una AcademyException, che è una Checked Exception e quindi devo gestirla (o in questo modo o con try/catch)
		                                                       //Se io non mettessi il throws, nelle righe successive non potrei indicare le mie Exceptions personalizzate
		if (usr.getNome() == null)
			throw new AcademyException("Nome non caricato"); //con throw new creo un'eccezione, con throws dico che lascio passare un'eccezione (vedi due righe fa)
		
		if (usr.getCognome() == null)
			throw new AcademyException("Cognome non caricato");
		
		if (usr.getSesso() == null)
			throw new AcademyException("Sesso non caricato");
		
	}

}
