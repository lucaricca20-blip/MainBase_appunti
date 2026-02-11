package com.betacom.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.betacom.dao.DipendentiDAO;
import com.betacom.models.dipendenti;
import com.betacom.singletone.SQLConfiguration;
import com.betacom.utils.SQLManager;

public class ServicesQuery {

	private SQLManager db = new SQLManager();
	private DipendentiDAO dao = new DipendentiDAO();

	public void executeQuery() {
		listTable();
		getAllDipendenti();
		getDipendentiWithParamters("impiegato", 1);
		getDipendentiById(1);
		getCount("impiegato", 1);
	}

	private void listTable() {
		try {
			List<String> lT = db.listOfTable("db_academy_01_2026");
			lT.forEach(t -> System.out.println(t));
		} catch (Exception e) {
			System.err.println("Error found:" + e.getMessage());
		}
	}

	private void getAllDipendenti() {
		System.out.println("getAllDipendenti **********");
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
	private void getDipendentiWithParamters (String param, Integer uff) {
		System.out.println("getDipendentiWithParamters **********");
		try {
			List <dipendenti> lD = dao.findGeneric("query.dipendenti.mansione", new Object [] {param, uff});
			lD.forEach(d -> System.out.println(d));
		} catch (Exception e) {
			System.err.println("Error found:" + e.getMessage());
		}

	}

	private void getDipendentiById (Integer id) {
		System.out.println("getDipendentiById **********");
		try {
			Optional<dipendenti> dip = dao.findById(id);
			if (dip.isEmpty())
				System.out.println("Dipendente non trovato: " +id);
			else
				System.out.println(dip.get());
		} catch (Exception e) {
			System.err.println("Error found:" + e.getMessage());
		}
	}
	
	private void getCount (String param, Integer uff) {
		System.out.println("getCount **********");
		try {
			Long c = dao.count("query.dipendenti.mansione", new Object [] {param, uff});
			System.out.println("Count dipendenti :" + c);
		} catch (Exception e) {
			System.err.println("Error found:" + e.getMessage());
		}

	}
}