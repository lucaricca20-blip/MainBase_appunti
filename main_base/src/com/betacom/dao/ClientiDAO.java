package com.betacom.dao;

import com.betacom.models.Clienti;
import com.betacom.singletone.SQLConfiguration;
import com.betacom.utils.SQLManager;

public class ClientiDAO {
	
	private SQLManager db = new SQLManager();
	
	public int insert(String qryName, Clienti cli) throws Exception {
		int numero = 0;
		
		Object [] params = new Object [] {
				cli.getDenominazione(),
				cli.getpIva(),
				cli.getIndirizzo(),
				cli.getTelefono(),
				cli.getIdComune()
		};
		
		String query = SQLConfiguration.getInstance().getQuert(qryName);
		System.out.println(query);
		
		return db.save(query, params, true); //posso recuperare la pk
	}

}
