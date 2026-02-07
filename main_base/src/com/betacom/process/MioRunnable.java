package com.betacom.process;

public class MioRunnable implements Runnable { // devo implementare runnable
	//Classe MioRunnable sarà il mio thread
	//Esempio con un ciclo semplice

	@Override
	public void run() {
		for (int i = 0; i <= 10; i++) {
			System.out.println("Runnable in esecuzione " +i);
		}
		
	}

}
