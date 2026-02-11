package com.betacom.services;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.betacom.dao.ClientiDAO;
import com.betacom.dao.DipendentiDAO;
import com.betacom.dao.RapportoClientiDAO;
import com.betacom.exceptions.AcademyException;
import com.betacom.models.Clienti;
import com.betacom.models.RapportoCliente;
import com.betacom.models.dipendenti;
import com.betacom.singletone.SQLConfiguration;
import com.betacom.utils.SQLManager;

public class ServicesTransaction {
	
	/*
	 * Vai a rivedere cosa sono le transazioni nel file specifico. Fino a quando non si fa il commit le 
	 * tabelle sul DB non vengono modificate pur facendo run.
	 */
	
	private final DipendentiDAO daoD;
	private final ClientiDAO daoC;
	private final RapportoClientiDAO daoR;
	private final SQLManager db;
	
	private static final Integer DIPENDENTE_ID = 30;
	

	public ServicesTransaction() { // l'ho creato io per inizializzarli dentro il costruttore (per non fare il new direttamente dopo il private final ecc)
		this.daoD = new DipendentiDAO();
		this.daoC = new ClientiDAO();
		this.daoR = new RapportoClientiDAO();
		this.db = new SQLManager();
	}
	
	public void executeTransaction() throws AcademyException {
		System.out.println("Begin transaction");
		try {
			SQLConfiguration.getInstance().setTransaction();
			
			int idCliente = createCliente();
			getDipendente(DIPENDENTE_ID);
			
			createRapportoCliente(idCliente);
			findAllRapporti();
			// generateError();
			
			
			db.commit();
		} catch (Exception e) {
			System.err.println("Error found: " +e.getMessage());
			System.err.println("Execute rollback..... "); //se qualcosa va storto, torna indietro, non salva nulla nel db. Salva solo quando fa commit. Questa è l'importanza delle transazioni
			
			db.rollback();
		} 
	}
	
	private int createCliente () throws Exception {
		System.out.println("Insert into clienti ********");
		
		int num = 0;
		
		Clienti cli = new Clienti("Cliente transaction", "piva02_03", "via della transazione, 154 Torino", "362517894", 1);
		
			num = daoC.insert("update.clienti.insert", cli);
			System.out.println("inserimento cliente: " +num);
			
			Optional<Clienti> c = daoC.findById(num);
			if (c.isEmpty())
				throw new AcademyException("Cliente non trovato: " +num);
			System.out.println(c.get()); //se lo trova
			
		return num;
	}
	
	private void getDipendente(int id) throws Exception {
		System.out.println("get dipendente********" +id);
		Optional<dipendenti> dip = daoD.findById(id);
		if (dip.isEmpty())
			throw new AcademyException("Dipendente non trovato: " +id);
		
		System.out.println(dip.get());
	}
	
	private void createRapportoCliente(int idCliente) throws Exception {
		System.out.println("INsert into RapportoCliente*******");
		
		RapportoCliente rap = new RapportoCliente("Rapporto Java", idCliente, DIPENDENTE_ID);
		
		int idRapporto = daoR.insert(rap);
		System.out.println("Rapporto cliente create: " +idRapporto);
		
		Optional<RapportoCliente> row = daoR.findById(idRapporto);
		if (row.isEmpty())
			throw new AcademyException("Rapporto non trovato");
		System.out.println(row.get());
	}
	
	private void findAllRapporti() throws Exception {
		System.out.println("Insert into findAllRapporti*******");
		List<RapportoCliente> lR = daoR.findAll();
		lR.forEach(r -> System.out.println(r));
	}
	
	private void generateError() throws Exception {
		throw new AcademyException("Error generated........");
	}

}
