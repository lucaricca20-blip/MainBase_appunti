package com.betacom.dao;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.betacom.models.dipendenti;
import com.betacom.singletone.SQLConfiguration;
import com.betacom.utils.SQLManager;
import static com.betacom.utils.Utilities.dateToLocalDate;

public class DipendentiDAO {

	private SQLManager db = new SQLManager();
	public List<dipendenti> findAll() throws Exception{
		
		String query = (SQLConfiguration.getInstance().getQuert("query.dependenti"));
		System.out.println(query);
		
		List<Map<String, Object>> lD = db.list(query);

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