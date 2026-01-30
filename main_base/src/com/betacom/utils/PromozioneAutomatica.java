package com.betacom.utils;

public class PromozioneAutomatica {
	
	
	//Un metodo STATIC mi permette di invocarlo anche senza creare un oggetto, scrivendo NomeClasse.nomemetodo dove c'è un main (dopo aver fatto import)
	private static final Integer MAX_USER = 10; //così non potra essere modificata perché c'è final. Se avessi messo int al posto di Integer non cambia niente
	
	public static void method (int i) {  //se scrivo void non ho nessun ritorno
		System.out.println("Sono un integer con il valore: " +i);
	}

	public static void method (double i) {
		System.out.println("Sono un double con il valore: " +i);
	}
	
	public static void method (Object i) {
		System.out.println("Sono un Object con il valore: " +i);
	}
	
	
}
