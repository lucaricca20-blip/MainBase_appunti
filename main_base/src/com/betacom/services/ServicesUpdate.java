package com.betacom.services;

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
			insertCliente();

		} catch (Exception e) {
			System.out.println("Error found: " + e.getMessage());
		}
	}

	private void insertCliente() throws AcademyException { //inserire record cliente in tabella del db (verifico su dbeaver)
		System.out.println("Insert into clienti ********");
		
		int num = 0;
		
		Clienti cli = new Clienti("Insert by java", "piva01", "via del test, 154 Belluna", "2dhbdjv", 1);
		
		
		try {
			num = dao.insert("update.clienti.insert", cli);
			System.out.println("inserimento cliente: " +num);
		} catch (Exception e) {
			System.out.println("Error found: " +e.getMessage());
		}
	}
}
