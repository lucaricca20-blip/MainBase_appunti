package com.betacom.process;

import com.betacom.objects.Inner;

import interfaces.ProcessInterface;

public class ProcessInner implements ProcessInterface {
	
	/*
	 * Vedi prima classe Inner.
	 */

	@Override
	public boolean execute() throws Exception {
		System.out.println("Begin ProcessInner");
		
		Inner inner = new Inner();
		inner.setFatherClass("Siamo nella class Padre");
		inner.setNumero(3);
		
		System.out.println("Class inner : " + inner.getFatherClass() + "numero: " + inner.getNumero());
		
		
		Inner.Figlio figlio = inner.setInstanceFiglio();  //Voglio creare istanza di Tipo Figlio. Figlio è una classe contenuta dentro Inner
		figlio.setFiglioClass("Sono nella classe Figlio");
		figlio.setNumero2(22);
		
		System.out.println("Valore del figlio: " + figlio.displayNumber());
		
		Inner.Figlio.Nipote nipote = figlio.setInstanceNipote();
		nipote.setNumero3(888);
		
		System.out.println("Valore del nipote: " + nipote.displayNumberNipote() );
		
		return false;
	}


}
