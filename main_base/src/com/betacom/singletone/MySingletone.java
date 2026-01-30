package com.betacom.singletone;

/*
 * Una classe singletone è una classe con cui non posso fare new direttamente da altra classe
 * infatti metto il costruttore private,cioè non posso creare oggetti direttamente di questa classe.
 * 
 */
public class MySingletone {
	
	private static MySingletone instance = null; //ho creato istanza di tipo Mysingletone
	private Integer idx = 0; //Guarda in basso, mi serve per il test che farò. Non è statica, quindi appartiene all'oggetto che creo e non alla classe.

	private MySingletone() { //costruttore. Ho messo private invece di public
	}
	
	/*
	 * Per poter usare questa classe faccio un metodo statico. Il metodo dice che se 
	 * lo chiamo crea per la priva volta l'istanza (dentro la classe posso crearla,
	 * infatti in alto ho creato private static Mysingleton) e la ritorna, se invece
	 * l'istanza non è null (è già stata creata) semplicemente ritorna l'istanza, non
	 * la ricrea.
	 */
	
	public static MySingletone getInstance() { //getInstance guarda se qualcuno ha già usato getInstance, se no crea nuovo oggetto e ritorna l'istanza
		if (instance == null) {
			instance = new MySingletone(); //è private, quindi qui posso creare istanza
		}
		return instance;
	}
	//Faccio un test, ma prima creo un metodo che mi dice che se chiamo questo metodo
	//mi ritorna idx aumentato di 1.Questo metodo lo chiamo prima in classe
	//CallSingletone. Vogliamo dimostrare che se una classe (anche di altro package) modifica
	//qualcosa nel singletone, tutte le altre classi vedono comunque quella modifica. Vedi
	//la classe ClassSingletone e classe ProcessSingleTone.
	public Integer computeIndice () {
		return ++idx;
	}

}
