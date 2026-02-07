package com.betacom.process;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import com.betacom.objects.Address;

import interfaces.ProcessInterface;

public class ProcessSerializzazione implements ProcessInterface {

	@Override
	public boolean execute() throws Exception {
		System.out.println("Begin ProcessSerializzazione");
		
		
		
		/*
		 * Gli oggetti java per poter essere trasferiti devono essere serializzati.
		 * Possono essere così trasferiti tra JVN o tra applicazioni. Così per es. posso verificare se io e
		 * il cliente abbiamo la stessa versione.
		 * Qui e in Address faccio la serializzazione, poi vai nel progetto projectDeserializzazione
		 * per passare dal file che crea nel percorso che indico a un oggetto java, ovvero fare l'operazione
		 * inversa.
		 */
		
		
		Address address = new Address();
		address.setCity("Roma");
		address.setName("Vincenzo Rossi");
		address.setSesso(true);
		address.setStreet("Via Torino");
		
		/*
		 * Con FileOutput stream il file si trasforma in formato binario
		 */
		
		try {
			FileOutputStream fout =  new FileOutputStream("C:/Users/Betacom/Desktop/Academy LR/provaSerializzazione");
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(address);
			oos.flush();
			oos.close();
			System.out.println("Object created....");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return false;
	}

}
