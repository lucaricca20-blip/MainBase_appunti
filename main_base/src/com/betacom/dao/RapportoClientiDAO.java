package com.betacom.dao;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.betacom.models.RapportoCliente;
import com.betacom.singletone.SQLConfiguration;
import com.betacom.utils.SQLManager;

public class RapportoClientiDAO {
	
	private final SQLManager db;
	
	public RapportoClientiDAO() {
		this.db = new SQLManager();
	}
	
	public int insert(RapportoCliente rap) throws Exception {
		String query = SQLConfiguration.getInstance().getQuert("insert.rapporti-clienti");
		System.out.println(query);
		System.out.println("parametri: " +rap);
		
		Object [] params = new Object[] {
				rap.getDescrizione(),
				rap.getIdCliente(),
				rap.getIdDipendente()
		};
		return db.save(query, params, true);
	}
	public List<RapportoCliente> findAll() throws Exception {
		List<Map<String, Object>> lR = db.list("select * from rapporto_cliente");
		return lR.stream()
				.map(row -> new RapportoCliente(
						(Integer) row.get("id_rapporto"),
						(String)row.get("descrizione"),
						(Integer)row.get("id_cliente"),
						(Integer)row.get("id_dipendente")))
				.collect(Collectors.toList());
	}
	public Optional<RapportoCliente> findById(Integer id) throws Exception {
		String query = SQLConfiguration.getInstance().getQuert("query.rapporto.clienti.byId");
		System.out.println(query);
		
		Object [] params = new Object [] {id};
		
		Map<String, Object> row = db.get(query, params);
		if (row  == null) 
			return Optional.empty(); //se non trovi niente
		
		return Optional.ofNullable(new RapportoCliente(
				(Integer) row.get("id_rapporto"),
				(String)row.get("descrizione"),
				(Integer)row.get("id_cliente"),
				(Integer)row.get("id_dipendente")));
	}
}
