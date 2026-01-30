package com.betacom.objects;

import interfaces.Preda;
import interfaces.Predatore;

public class Pesce implements Preda, Predatore {

	@Override
	public void sonoUnPredatore() {
		System.out.println("Pesce: sono un predatore");
		
	}

	@Override
	public void sonoUnaPreda() {
		System.out.println("Pesce: sono una preda");
		
	}

}
