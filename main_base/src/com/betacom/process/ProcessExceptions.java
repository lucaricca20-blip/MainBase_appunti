package com.betacom.process;

import com.betacom.exceptions.AcademyException;
import com.betacom.objects.User;

import interfaces.ProcessInterface;

public class ProcessExceptions implements ProcessInterface {

	@Override
	public boolean execute () throws Exception, AcademyException {
		System.out.println("Begin ProcessExceptions");
		
		
		int p1 = 10;
		int p2 = 0; //Se qui mettessi 0, mi darebbe un'ArithmeticException
		
		/*
		 * Posso usare il try così so subito per quale istruzione (p1/p2) il sistema mi va
		 * in tilt. Se metto p2 = 0 sopra, avrò il messaggio "abbiamo avuto....", come ho
		 * impostato di seguito.
		 */
		
		
			int res = p1/p2;

		
		User usr = new User();
		ValidationUser v = new ValidationUser(); //Devo cliccare su v per scegliere di far comparire in automatico try e catch sotto. sarà con questo oggetto che userò metodo checkUSer
		usr.setNome("Bruno");
		usr.setCognome("Caligero"); //Se non valorizzassi uno dei tre attributi, mi comparirebbe eccezione che ho creato in AcademyException (e poi ho implementato in ValidationUser)
		usr.setSesso(true);
		
		try {
			v.checkUser(usr); //usa il metodo checkUser con l'oggetto usr (di classe User). Il metodo checkUser è in ValidationUser.java
		} catch (AcademyException e) { //Se si verifica una delle exception indicate in checkUser (che a sua volta le riporta in AcademyException) allora gestiscila come dico dentro al catch
			throw new AcademyException(e.getMessage()); //gestisci la exception inviando un messaggio (è come se fosse un'altra exception dato che uso throw new. Così mi avvisa e mi rilancia nuovamente l'exception
		}
		System.out.println("User validato normalmente"); //Se tutto procede bene, stampa questo messaggio (se il try non va tutto lisci ma trova eccezioni, il codice non arriva fin qui)
		
		return true;
	}

}
