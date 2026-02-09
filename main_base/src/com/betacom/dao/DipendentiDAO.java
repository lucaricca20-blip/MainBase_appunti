package com.betacom.dao;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.betacom.models.dipendenti;
import com.betacom.singletone.SQLConfiguration;
import com.betacom.utils.SQLManager;
import static com.betacom.utils.Utilities.dateToLocalDate;

public class DipendentiDAO {
	
	/*
	 * Il DAO è un oggetto che gestisce in modo completo un DB. Allora di seguito devo prima leggere la query (che è nel file
	 * query.properties), la stampo.
	 */

	private SQLManager db = new SQLManager();
	public List<dipendenti> findAll() throws Exception{
		
		String query = (SQLConfiguration.getInstance().getQuert("query.dependenti"));
		System.out.println(query);
		
		List<Map<String, Object>> lD = db.list(query);
		
		return resultToObject(lD);


	}
	
	public List<dipendenti> findGeneric (String qryName, Object[] params) throws Exception {
		String query = SQLConfiguration.getInstance().getQuert(qryName);
		System.out.println(query);
		
		List<Map<String, Object>> lD = db.list(query, params);
		
		return resultToObject(lD);
	}
	
	public Optional<dipendenti> findById(Object[] params) throws Exception {
		String query = SQLConfiguration.getInstance().getQuert("query.dipendenti.byId");
		System.out.println(query);
		
		Map<String, Object> di = db.get(query, params);
		if (di  == null) 
			return Optional.empty(); //se non trovi niente
		
		return Optional.ofNullable(new dipendenti(  //se trovi qualcosa:
				(Integer)di.get("id_dipendente"),
				(String)di.get("nome"),
				(String)di.get("cognome"),
				dateToLocalDate(di.get("data_assunzione")),
				(String)di.get("telefono"),
				(String)di.get("manzione"),
				((BigDecimal)di.get("stipendio")).doubleValue(),
				(Integer)di.get("id_ufficio"),
				(String)di.get("code")
				));
	}
	
	public Long count(String qryName, Object[] params) throws Exception {
		String query = SQLConfiguration.getInstance().getQuert(qryName);
		System.out.println(query);
		return db.count(query, params);
	}
	
	private List<dipendenti> resultToObject (List<Map<String, Object>> lD) {
		return lD.stream()
				.map(row -> new dipendenti(
						(Integer)row.get("id_dipendente"),
						(String)row.get("nome"),
						(String)row.get("cognome"),
						dateToLocalDate(row.get("data_assunzione")),
						(String)row.get("telefono"),
						(String)row.get("manzione"),
						((BigDecimal)row.get("stipendio")).doubleValue(),
						(Integer)row.get("id_ufficio"),
						(String)row.get("code")
						)).collect(Collectors.toList());
	}
}