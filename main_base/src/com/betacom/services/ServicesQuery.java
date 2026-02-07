package com.betacom.services;

import java.util.List;
import java.util.Map;

import com.betacom.dao.DipendentiDAO;
import com.betacom.models.dipendenti;
import com.betacom.singletone.SQLConfiguration;
import com.betacom.utils.SQLManager;

public class ServicesQuery {

	private SQLManager db = new SQLManager();
	private DipendentiDAO dao = new DipendentiDAO();
	
	public void executeQuery() {
		listTable();
		getDipendenti();
	}
	
	private void listTable() {
		try {
			List<String> lT = db.listOfTable("db_academy_01_2026");
			lT.forEach(t -> System.out.println(t));
		} catch (Exception e) {
			System.err.println("Error found:" + e.getMessage());
		}
	}
	
	private void getDipendenti() {
		/*
		String query = (SQLConfiguration.getInstance().getQuert("query.dependenti"));
		System.out.println(query);
	
		List<Map<String, Object>> res = db.list(query);
		
		System.out.println("Numero di righe:" + res.size());
		
		for (Map<String, Object> it:res) {
			System.out.println(it.get("nome") + " " + it.get("cognome"));
		}
		*/
		try {
			List<dipendenti> lD = dao.findAll();
			lD.forEach(d -> System.out.println(d));
			
			
		} catch (Exception e) {
			System.err.println("Error found:" + e.getMessage());
		}
		
		
		
	}
	
}