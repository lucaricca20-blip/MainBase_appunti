package com.betacom.singletone;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.betacom.exceptions.AcademyException;
import com.betacom.utils.SQLManager;

public class SQLConfiguration {
	
	//Parti da qui per capire connessione di DB con Java (JDBC): https://claude.ai/share/92665734-5e12-43b5-b532-c0f7527a80af
	
	/*
	 * Riepilogo del flusso completo
ProcessSQL → chiama SQLConfiguration (Singleton) che carica i file .properties → chiama SQLManager che si connette al DB e 
esegue le query → ServicesQuery coordina le operazioni → DipendentiDAO trasforma i dati grezzi in oggetti dipendenti (Model)
 → i risultati vengono stampati.
È un'architettura a strati ben organizzata: configurazione → connessione → esecuzione query → trasformazione dati → 
presentazione.

Vedi pure la cartella in appunti DB con gli screen
	 */
	
	private static SQLConfiguration instance = null;
	private static Properties prop = new Properties();
	private static Properties queries = new Properties();
	
	private Connection con = null;
	
	private SQLConfiguration() {		
	}
	
	public static SQLConfiguration getInstance() throws AcademyException {
		if (instance == null) {
			instance = new SQLConfiguration();
			loadConfiguration();
		}
		return instance;
	}

	private static void loadConfiguration() throws AcademyException{
		
		try {
			InputStream input = new FileInputStream("src/sql.properties");
			prop.load(input);

			InputStream sql = new FileInputStream("src/query.properties");
			queries.load(sql);

		
		} catch (IOException e) {
			throw new AcademyException(e.getMessage());		
		}
	}
	
	public String getProperty(String p) {
		return prop.getProperty(p);
	}

	public String getQuert(String p) {
		return queries.getProperty(p);
	}
	
	/*
	 * Close connection (es. per cambiare db. Es. da mySQL a PostGres). è l'opposto di getConnection del 
	 * singleton (in quel caso, se non c'è connessione al db attiva, ne avvia una nuova).
	 */
	
	public void closeConnection() throws AcademyException {
		try {
			if (con != null) // se la connessione non è null (già chiusa)
				con.close();
			con = null;
		} catch (Exception e) {
			throw new AcademyException("close connection error: " + e.getLocalizedMessage());
		}
	}

	public Connection getConnection() throws AcademyException{
		if (con == null) {
			con = new SQLManager().getConnection();
		}		
		return con;
	}
	
	public void setAutoCommit() throws SQLException { //per avere aggiornamenti subito disponibili
		con.setAutoCommit(true);
	}
	public void setTransaction() throws SQLException { //se non faccio niente, non fa commit di niente
		con.setAutoCommit(false);
	}
}
