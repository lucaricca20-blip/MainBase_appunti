package com.betacom.process;

import java.util.ArrayList;

import com.betacom.objects.ClassInterface;
import com.betacom.objects.ClassInterface1;

import interfaces.ProcessInterface;
import interfaces.SampleInterface;
import interfaces.SampleInterface1; //capita a volte che non mi suggerisca import come questo, in quel caso lo creo manualmente

/*
 * RICORDA: UNA CLASSE PUò IMPLEMENTARE PIù INTERFACCE MENTRE PUò ESTENDERE SOLO UNA CLASSE (ANCHE SE ASTRATTA)
 * NELLE INTERFACCE METTO SOLO METODI E COSTANTI, MENTRE IN CLASSI ASTRATTE POSSO METTERE PURE ATTRIBUTI E COSTRUTTORI
 */
public class Process_Interface_Main implements ProcessInterface {

	@Override
	public boolean execute() throws Exception { //Prima usava main, ora ha messo tutto dentro questo override di ProcessInterface. Poi dovrei metterlo in mainProcess (ma non lo faccio) in MainProcess e l'ho messo in riga 52
		
		SampleInterface sI = new ClassInterface (); // devo crearlo con ClassInterface che è una classe, non posso istanziare direttamente Sample Int perché è interfaccia
		
		sI.interface1("primo");
		sI.interface2("secondo");
		
		SampleInterface1 sI1 = new ClassInterface1();
		sI1.test1("test 1");
		sI1.test1("test 2");
		
		SampleInterface1 sII = new ClassInterface();
		sII.test1("ppppp");
		sII.test2("zzzz");
		
		ClassInterface cl = new ClassInterface();
		cl.interface1("zzz");
		cl.interface2("yyy");
		cl.test1("tttt");
		cl.test2("zzz");
		
		ClassInterface1 cl1 = new ClassInterface1();
		cl.interface1("zzz");
		cl.interface2("yyy");
		cl.test1("tttt");
		cl.test2("zzz");
		
		
		identificate(cl1); //è il metodo che ho messo fuori dal main. cl è un oggetto e quindi lo accetta
                           //cl1 non è un'istanza di SampleInterface ma di Object implements SampleInterface1 quindi metodo stamperà solo questo
     
		System.out.println("Leggi da qui in poi:\n");
		ArrayList <Object> l0 = new ArrayList <Object> (); //potrei fare questo: carico i due oggetti nell'arraylist e faccio i for per vedere di che interfacce
		l0.add(cl);                                        
		l0.add(cl1);
		
		
		for (Object obj:l0)  {  //Prendi ogni elemento della lista l0, chiamalo obj e fai il blocco di codice dentro le parentesi graffe.
			
			if (obj instanceof SampleInterface) {
				SampleInterface oo =(SampleInterface)obj;
				oo.interface1("Da loop");
				oo.interface2("Da loop");
			}
			if (obj instanceof SampleInterface1) {
				
				SampleInterface1 oo =(SampleInterface1)obj;
				oo.test1("Da loop");
				oo.test2("Da loop");
			}
		}
		return true;
		
	
	}

	
	/*
	 * Object davanti a qualcosa significa che quella variabile in realtà è un oggetto. Nel main avrei potuto scrivere ad esempio
	 * 
	 * Object sI = new ClassInterface();
	 * SampleInterfacesI = (SampleInterface)sI;  ciò mi serve per fare un cast di sI
	 */
	private static void identificate (Object o) {  //l'ho messo fuori dal metodo. Object o mi permette di chiamare facilmente il metodo
	    if(o instanceof SampleInterface) { //L'oggetto chiamato dal metodo è un'istanza di SampleInterface? Vedi meglio spiegazione in MainEsercizio1.java
	    	System.out.println("Object implements SampleInterface");
	    }	
		if(o instanceof SampleInterface1) {
		    System.out.println("Object implements SampleInterface1");
	    
	    }
	}
}



