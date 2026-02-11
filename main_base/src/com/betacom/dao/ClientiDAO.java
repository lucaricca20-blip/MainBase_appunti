package com.betacom.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.betacom.exceptions.AcademyException;
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
		
		return db.save(query, params, true); //posso recuperare la pk. La chiave nel DB è auto_increment quindi non sappiamo quale sia
	}
	
	public int update(Clienti cli) throws Exception {
		if (cli.getId_cliente() == null) {
			throw new AcademyException("ID null");
		}
		String query = "update clienti set ";
		String separator = "";
		List<Object> params = new ArrayList<Object>();
		
		if (cli.getDenominazione() != null) {
			query = query + separator + "denominazione = ?";
			params.add(cli.getDenominazione());
			separator = " , ";
		}
		if (cli.getIndirizzo() != null) {
			query = query + separator + "indirizzo = ?";
			params.add(cli.getIndirizzo());
			separator = " , ";
		}
		if (cli.getpIva() != null) {
			query = query + separator + "p_iva = ?";
			params.add(cli.getpIva());
			separator = " , ";
		}
		if (cli.getTelefono() != null) {
			query = query + separator + "telefono = ?";
			params.add(cli.getTelefono());
			separator = " , ";
		}
		if (cli.getIdComune() != null) {
			query = query + separator + "id_comune = ?";
			params.add(cli.getIdComune());
			separator = " , ";
		}
		if (cli.getId_cliente() == null)
			throw new AcademyException("ID null");
		query = query + " where id_cliente = ?";
		params.add(cli.getId_cliente());
		
		Object [] paramsArray = params.toArray();
		
		System.out.println("SQL generato: " +query);

		return db.save(query, paramsArray);
	}
	
	public int delete(Integer id) throws Exception {
		if (id == null)
			throw new AcademyException("Id non è stato caricato");
		
		Object [] params = new Object [] {id};
		String query = "delete from clienti where id_cliente = ?";
		
		return db.save(query, params);
		
	}
	
	public List<Clienti> findAll () throws Exception {
		String query = SQLConfiguration.getInstance().getQuert("query.clienti");
		System.out.println(query);
		
		List<Map<String, Object>> lC = db.list(query);
		
		return lC.stream()
				.map (row -> new Clienti(
						(Integer)row.get("id_cliente"), 
						(String)row.get("denominazione"), 
						(String)row.get("p_iva"), 
						(String)row.get("indirizzo"), 
						(String)row.get("telefono"), 
						(Integer)row.get("id_comune"))).collect(Collectors.toList());
				
				
				
	}
	
	public Optional<Clienti> findById(Integer id) throws Exception {
		String query = SQLConfiguration.getInstance().getQuert("query.clienti.byId");
		System.out.println(query);
		Object [] params = new Object [] {id};
		
		Map<String, Object> row = db.get(query, params);
		if (row == null) 
			return Optional.empty(); //se non trovi niente
		
		return Optional.ofNullable(new Clienti(  //se trovi qualcosa:
				(Integer)row.get("id_cliente"), 
				(String)row.get("denominazione"), 
				(String)row.get("p_iva"), 
				(String)row.get("indirizzo"), 
				(String)row.get("telefono"), 
				(Integer)row.get("id_comune")));
	}

}
