package com.betacom;

import com.betacom.objects.Impiegato;

public class MainEredit {

	public static void main(String[] args) {
		System.out.println("Begin MainEredit");
		Impiegato imp = new Impiegato ("Gianni","Laverdura",true,1500.0);
		
		System.out.println(imp);
		
		imp = new Impiegato(); //Per fare in modo che non dia errore, devo mettere pure un costruttore vuoto nella classe Impiegato
		imp.setCognome("Bianco");
		imp.setNome("Lucia");
		imp.setSesso(true);
		imp.setSalary(1300.00);
		
		System.out.println(imp);
		
		System.out.println(imp.getSesso());
				


	}

}
