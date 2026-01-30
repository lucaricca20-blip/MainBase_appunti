package com.betacom;

import com.betacom.objects.BMW;
import com.betacom.objects.Car;
import com.betacom.objects.Fiat500;

public class MainAbstract {

	public static void main(String[] args) {
		
	// non posso fare	Car a = new Car (); perché Car è classe astratta
		
		Fiat500 fiat = new Fiat500();
		fiat.setColor("Nera");
		fiat.setMaxSpeed(180);
		fiat.setModel("Fiat 500");
		
		fiat.frena();
		fiat.accelera();
		
		System.out.println("Model :" + fiat.getModel());
		
		
		BMW bmw = new BMW (); //devo fare l'import posizionandomi su BMW
		
		bmw.setColor("Bianca");
		bmw.setMaxSpeed(250);
		bmw.setModel("M3");
		
		bmw.frena();
		bmw.accelera();
		
		System.out.println("Model :" + bmw.getModel());
	}

}
