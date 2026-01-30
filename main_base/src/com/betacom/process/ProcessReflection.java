package com.betacom.process;

import java.io.Console;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

import com.betacom.enums.Reparto;

import interfaces.ProcessInterface;

public class ProcessReflection implements ProcessInterface {

	@Override
	public boolean execute() throws Exception {
		System.out.println("Begin ProcessReflection");
//		/*
//		 * La Reflection mi serve per fare introspezione delle classi.
//		 */
//
//		String packageName = "com.betacom.objects"; //package di MyClassReflection
//		String className = "MyClassReflection";
//
//		try { //Conviene sempre fare il try per capire, se c'è un problema, dove si trova
//			Class cl = Class.forName(packageName + "." + className); //dico dove si trova la classe
//			System.out.println("Class " + className + " found");
//
//			/*
//			 * retrieve constructor for selected class
//			 */
//
//			Constructor [] ctore = cl.getConstructors(); //mette i costruttori di MyrRef... in ctore
//			Constructor selectedConstructor = null;
//			int constructorToSelect = 0;
//			Object myClass = null;
//
//			System.out.println("numero di constructors trovati: " +ctore.length); //Vede quanti costruttori ci sono nelle classe MyReflection (2); poi ne ho aggiunto un terzo
//
//			for (Constructor ctore1:ctore) { //faccio introspezione dei costruttori
//				/*
//				 * search parameters number for all constructors
//				 * Mi dice quanti parametri hanno i costruttori di quella classe:
//				 * Infatti mi dice che uno ha 3 parametri e uno zero, infatti
//				 * c'è anche un costruttore vuoto in MyClassReflection
//				 * 
//				 */
//
//				System.out.println("..Parameters number for constructor: " + ctore1.getParameterCount());
//
//				if (ctore1.getParameterCount() == constructorToSelect) {
//					selectedConstructor = ctore1;
//					System.out.println("constructor selected");
//				}
//				/*
//				 * Adesso, solo a scopo informativo, voglio sapere che tipi
//				 * di parametri ci sono nel costruttore. Mi stamperà che il
//				 * costruttore ha due Integer e uno String (vai a vedere nella
//				 * classe).
//				 */
//
//				Type [] type = ctore1.getGenericParameterTypes(); //Ci serve solo per informazione (Type importalo da java.lang.reflect, non da altri)
//				for (Type t:type) { //Qui non conviene usare forEach perché altrimenti avremmo oggetti annidati
//					System.out.println("....Parameter type: " +t);
//				}
//			}
//
//			/*
//			 * new instance
//			 */
//
//			if (constructorToSelect ==0)  {
//				myClass = selectedConstructor.newInstance(); //myClass deve essere un Object, altrimenti se è un Class va in errore. Ho messo che deve avere 0 costruttori, quindi tra parentesi lascio vuoto
//				System.out.println("select constructor without parameter");
//			}
//			if (constructorToSelect ==2)  {
//				myClass = selectedConstructor.newInstance(10, "Description numero 1"); 
//				System.out.println("select constructor without parameter");
//			}
//			if (constructorToSelect ==3)  {
//				myClass = selectedConstructor.newInstance(10, "Description numero 2", 20); 
//				System.out.println("select constructor without parameter");
//			}
//
//			/*
//			 * Introspezione dei metodi
//			 */
//
//			Method [] methods = myClass.getClass().getMethods();
//			for (Method method:methods) {
//				System.out.println(".. method found: " + method.getName());
//				
//				if ("setId".equals(method.getName())) {
//					method.invoke(myClass, 99);
//				}
//				if ("setDesc".equals(method.getName())) {
//					method.invoke(myClass, "description caricato dentro loop");
//				}
//				if ("setP1".equals(method.getName())) {
//					method.invoke(myClass, 99999);
//				}
//			}
//			
//			String methodName = "toString";
//			Method metodo = myClass.getClass().getMethod(methodName);
//			String msg = (String)metodo.invoke(myClass);
//			
//			System.out.println(msg);
//
//
//		} catch (ClassNotFoundException e) {
//			System.out.println("ClassNotFoundException: " +e.getMessage());
//		} catch (IllegalArgumentException e) {
//			System.out.println("IllegalArgumentException: " +e.getMessage());
//		}
		
		executeImpiegato();
		
		return false;
	}
	
	public void executeImpiegato() throws Exception {
		System.out.println("Begin ProcessReflection");
		
		String packageName = "com.betacom.objects";
		String className = "Impiegato";
		
		try {
			Class cl = Class.forName(packageName + "." + className);
			System.out.println("Class " + className + " found");			
			
			/*
			 * retrieve constructor from selected class
			 */
			Constructor[] ctore = cl.getConstructors(); //mette tutti i costruttori della classe cl dentro ctore
			Constructor selectedConstructor = null;
			/*
			 * Con constructorToSelect seguente selezionerò quale costruttore voglio
			 * scegliere tra i diversi disponibili per classe Impiegato (ne ha 4). Quindi
			 * metterò o 0 o 1 o 4 o 5 in base a quello che poi voglio stampare.
			 */
			int constructorToSelect = 5; // 0, 1 ,4 ,5. AL momento seleziono 5
			Object myClass = null; //mi servirà dopo per creare nuovi oggetti. Ricorda che Object può contenere qualsiasi tipo di oggetto, quindi scrivo Object perché ancora non so cosa dovrà contenere
			
			System.out.println("numero di costruttori trovato: " + ctore.length);
			
			/*
			 * Adesso scorro tutti i costruttori che ha messo dentro l'array ctore e
			 * intanto stampo quanti costruttori ci sono.
			 * Poi con IF gli dico che se uno dei costruttori che ha trovato ha un 
			 * numero di parametri uguale a quello che ho impostato in
			 * constructorToSelect (es. io ho scritto 5, vedi in alto) allora
			 * deve mettere questo costruttore dentro al Constructor 
			 * selectedConstructor.
			 */
			for (Constructor ctore1:ctore) {
				
				/*
				 * search parameters number for all constructors
				 */
				System.out.println("..Parameters number for constructor: " + ctore1.getParameterCount());
				
				if(ctore1.getParameterCount() == constructorToSelect)
					selectedConstructor = ctore1;
				/*
				 * Il Type e il for seguente ci servono solo per capire quali tipi
				 * ci sono dentro il costruttore (es. String, Boolean, Double ecc).
				 */
				Type[] type = ctore1.getGenericParameterTypes();
				
				for (Type t:type) {
					System.out.println("....Parameters type: " + t);
				}
			}
			
			/*
			 * new instance
			 * Adesso voglio creare un'istanza di quella classe con il costruttore
			 * che ho selezionato (con 5 parametri).
			 */
			if (constructorToSelect == 0) {
				myClass = selectedConstructor.newInstance();
				System.out.println("select constructor without parameter");
			}
			if (constructorToSelect == 1) {
				myClass = selectedConstructor.newInstance(1900.0);
				System.out.println("select constructor with 1 parameter");
			}
			if (constructorToSelect == 4) {
				myClass = selectedConstructor.newInstance("Gerry", "Scotti", true, 1200.0);
				System.out.println("select constructor with 4 parameter");
			}
			if (constructorToSelect == 5) {
				myClass = selectedConstructor.newInstance("Paolo", "Bonolis", true, 1000.0, "IT");
				System.out.println("select constructor with 5 parameter");
			}
			
			/*
			 * introspection method
			 */
			Method[] methods = myClass.getClass().getMethods();
			for(Method m:methods) {
				System.out.println("... method found: " + m.getName());
				
				if("setReparto".equals(m.getName())) {
					m.invoke(myClass, Reparto.LOGISTICA);
				}
				
				if("setSesso".equals(m.getName())) {
					m.invoke(myClass, true);
				}
				
				if("setSalario".equals(m.getName())) {
					m.invoke(myClass, 1590.0);
				}
				if("setNome".equals(m.getName())) {
					m.invoke(myClass, "Leroy");
				}
				if("setCognome".equals(m.getName())) {
					m.invoke(myClass, "Jenkins");
				}
			}
			
			String methodName = "toString";
			Method metodo = myClass.getClass().getMethod(methodName);
			String msg = (String) metodo.invoke(myClass);

			System.out.println(msg);
		}
		catch (ClassNotFoundException e) {
			System.out.println("ClassNotFound:" + e.getMessage());
		}
		catch (IllegalArgumentException e) {
			System.out.println("IllegalArgument:" + e.getMessage());
		}
		
	}

}
