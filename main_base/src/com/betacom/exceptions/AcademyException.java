package com.betacom.exceptions;

public class AcademyException extends Exception { //Exception è una classe nativa

	public AcademyException() {
		super();
	}
	
	public AcademyException(String message) { //Accetta una stringa come messaggio
		super(message); //Così passiamo alla classe madre nativa Exception la stringa, in modo che quando in MainProcess.java usiamo e.getMessage(), questo mi faccia apparire tale messaggio
	                    //se non lo passassi a Exception, non lo vedrei poi il messaggio con e.getMessage
	}

	

	
}
