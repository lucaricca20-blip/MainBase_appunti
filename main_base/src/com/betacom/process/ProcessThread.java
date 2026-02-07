package com.betacom.process;

import interfaces.ProcessInterface;

public class ProcessThread implements ProcessInterface { 
	
	//vedi prima classi MioRunnable e MioSecondoRunnable in Process
	
	//Per capire vedi questa conversazione su Claude https://claude.ai/share/707bb214-e6c6-4927-9da6-4ef6ea9f1350


	@Override
	public boolean execute() throws Exception {
		System.out.println("Begin ProcessThread");
		
		Thread t = new Thread(new MioRunnable());
		Thread t1 = new Thread(new MioSecondoRunnable());
		
		
		t.start(); //fa partire il thread
		t1.start(); //secondo thread parte
		System.out.println("Thread is started...");
		
		/*
		 * dopo che il primo thread (t) parte, java non aspetta l'esecuzione del codice del primo thread
		 * ma fa partire direttamente il secondo thread. Quindi nella console i due vengono mischiati (ogni
		 * volta in modo diverso, è imprevedibile la sua azione).
		 */

		
		
		return false;
	}

}
