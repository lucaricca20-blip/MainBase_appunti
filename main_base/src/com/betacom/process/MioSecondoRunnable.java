package com.betacom.process;

public class MioSecondoRunnable implements Runnable {

	@Override
	public void run() {
		for (int i = 0; i <= 10; i++) {
			System.out.println("MioSecondoRunnable in esecuzione " +i);
		}
		try {
			Thread.sleep(4*1000); //lo faccio fermare per 4 secondi (è in millisecondi, quindi 4 per 1000), quindi gli sto dicendo di far partire il secondo ciclo dopo 4 secondi aver finito il primo
		} catch (InterruptedException e){

		}
		for (int i = 0; i <= 15; i++) {
			System.out.println("MioSecondoRunnable in esecuzione " +i);
		}
		System.out.println("******* Fine del thread MioSecondoRunnable");

	}
}
