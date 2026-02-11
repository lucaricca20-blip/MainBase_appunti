package com.betacom.services;

import java.util.List;
import java.util.Optional;

import com.betacom.dao.ClientiDAO;
import com.betacom.exceptions.AcademyException;
import com.betacom.models.Clienti;
import com.betacom.singletone.SQLConfiguration;
import com.betacom.utils.SQLManager;

public class ServicesUpdate {
	
	private ClientiDAO dao = new ClientiDAO();

	private SQLManager db = new SQLManager();

	public void executeUpdate() {

		try {
			SQLConfiguration.getInstance().setAutoCommit(); // autocommit is setted
			int id = insertCliente();
			updateCliente(id);
			delete(id);

		} catch (Exception e) {
			System.err.println("Error found: " + e.getMessage());
		}
	}

	private int insertCliente() throws AcademyException { //inserire record cliente in tabella del db (verifico su dbeaver)
		System.out.println("Insert into clienti ********");
		
		int num = 0;
		
		Clienti cli = new Clienti("Insert by java", "piva01", "via del test, 154 Belluna", "2dhbdjv", 1);
		
		
		try {
			num = dao.insert("update.clienti.insert", cli);
			System.out.println("inserimento cliente: " +num);
			List<Clienti> lC = dao.findAll();
			lC.forEach(c -> System.out.println(c));
			
			return num;
		} catch (Exception e) {
			System.err.println("Error found: " +e.getMessage());
		}	
		return num;
	}
	
//	private void updateCliente(int id) {
//		System.out.println("Insert into clienti *******" +id);
//		Clienti cli = new Clienti();
//		cli.setId_cliente(id); //eseguo questo metodo subito dopo la insert, e quando faccio run prima fa la insert e poi automaticamente modifica record con quell'id
//		cli.setIndirizzo("Indirizzo modificato");
//		cli.setTelefono("modificato");
//		
//		try {
//			int num = dao.update(cli);
//		} catch (Exception e) {
//			System.out.println("Error found: " +e.getMessage());
//		}
//	}
	
	private void updateCliente(int id) {
		System.out.println("Insert into clienti *******" +id);
		Clienti cli = new Clienti();
		cli.setId_cliente(id); //eseguo questo metodo subito dopo la insert, e quando faccio run prima fa la insert e poi automaticamente modifica record con quell'id
		cli.setIndirizzo("Indirizzo modificato");
		
		try {
			System.out.println("numero di righe modificate: " + dao.update(cli));
			Optional<Clienti>  row = dao.findById(id);
			if (row.isPresent())
				System.out.println(row.get());
		} catch (Exception e) {
			System.err.println("Error found: " +e.getMessage());
		}
	}
	
	private void delete (int id) {
		System.out.println("Delete clienti *******" +id);
		
		try {
			System.out.println("Numero di righe cancellate: " +dao.delete(id));
		} catch (Exception e) {
			System.err.println("Error found: " +e.getMessage());
		}
	}
}
