package com.betacom.other;

import com.betacom.singletone.MySingletone;

public class CallSingletone {
	
	/*
	 * Nel metodo seguente
	 */
	public void test () {
		Integer i = MySingletone.getInstance().computeIndice();
		System.out.println("Valore di indice dentro CallSingletone:" +i);
		
		
		
	}

}
