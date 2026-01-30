package com.betacom.objects;

public class Fiat500 extends Car { //mi dà errore all'inizio, poi mi posiziono su Fiat 500 e nella finestra dico di implementare i due metodi accelera e frena, di cui farà override

	@Override
	public void frena() {
		System.out.println("La Fiat 500 frena...."); //adesso posso implementare il metodo che era astratto nella classe Car
		
	}

	@Override
	public void accelera() {
		System.out.println("La Fiat 500 accelera....");
		
	}

}
