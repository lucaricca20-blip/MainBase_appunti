package com.betacom.utils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.betacom.exceptions.AcademyException;
import com.betacom.singletone.SQLConfiguration;
import com.mysql.cj.protocol.Resultset;

public class SQLManager {

	public Connection getConnection() throws AcademyException{
		Connection con = null;

		try {

			Class.forName(SQLConfiguration.getInstance().getProperty("driver"));  // load jdbc driver using reflection

			// ope connection with database (url, user, pwd)

			con = DriverManager.getConnection(
					SQLConfiguration.getInstance().getProperty("url"),
					SQLConfiguration.getInstance().getProperty("user"),
					SQLConfiguration.getInstance().getProperty("pwd")
					);


		} catch (Exception e) {
			throw new AcademyException(e.getMessage());
		}

		return con;
	}

	/*
	 * Table List
	 */
	public List<String> listOfTable(String dbName) throws AcademyException{

		List<String> lT = new ArrayList<String>();
		try {
			DatabaseMetaData dbMD = SQLConfiguration.getInstance().getConnection().getMetaData();

			ResultSet  res = dbMD.getTables(dbName, null, null, null);			
			// build del result			
			while(res.next()) {
				lT.add(res.getString("TABLE_name"));
			}

		} catch (SQLException e) {
			throw new AcademyException(e.getMessage());
		}
		return lT;
	}

	/*
	 * query without parameter
	 */
	public List<Map<String, Object>> list(String query) throws AcademyException{
		try {
			PreparedStatement cmd = SQLConfiguration.getInstance().getConnection().prepareStatement(query); // compile query

			ResultSet res = cmd.executeQuery();

			return resultsetToList(res);

		} catch (Exception e) {
			throw new AcademyException(e.getMessage());
		}


	}

	/*
	 * Query with single result object (quindi nella primary key ho solo un risultato)
	 */

	public Map<String, Object> get(String query, Object[] params) throws AcademyException{
		try {
			PreparedStatement cmd = SQLConfiguration.getInstance().getConnection().prepareStatement(query); // compile query

			cmd = createSet(cmd, params);
			ResultSet res = cmd.executeQuery();

			return resultsetToMap(res);

		} catch (Exception e) {
			throw new AcademyException(e.getMessage());
		}
	}
	
	/*
	 * Count with parameters
	 */
	
	public Long count(String query, Object[] params) throws AcademyException {
		try {
			String qryCount = "select count(*) as numero from ( " + query + " ) as numero";  
			
			PreparedStatement cmd = SQLConfiguration.getInstance().getConnection().prepareStatement(qryCount); // compile query

			cmd = createSet(cmd, params);
			ResultSet res = cmd.executeQuery();
			
			res.next();
			
			return res.getLong("numero");

		} catch (Exception e) {
			throw new AcademyException(e.getMessage());
		}
	}
	
	/*
	 * save without primary key
	 */
	
	public int save(String query, Object[] params) throws AcademyException {
		int ret = 0;
		
		
		try {
			
			PreparedStatement cmd = SQLConfiguration.getInstance().getConnection().prepareStatement(query); // compile query
			cmd = createSet(cmd, params);
			
			ret = cmd.executeUpdate();

		} catch (Exception e) {
			throw new AcademyException(e.getMessage());
		}
		
		return ret;

	}
	
	
	
	/*
	 * save with primary key (insert update)
	 */
	
	public int save(String query, Object[] params, boolean pk) throws AcademyException {
		int ret = 0;
		
		
		try {
			
			PreparedStatement cmd = SQLConfiguration.getInstance().getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS); // compile query
			cmd = createSet(cmd, params);
			
			ret = cmd.executeUpdate();
			
			ResultSet generatedKeys = cmd.getGeneratedKeys();
			if (generatedKeys.next())
				ret = generatedKeys.getInt(1);
			else
				throw new AcademyException("Generated key non validata..");

		} catch (Exception e) {
			throw new AcademyException(e.getMessage());
		}
		
		return ret;

	}

	/*
	 * Query with parameters. Devo aggiornare preparedstatement
	 */

	public List<Map<String, Object>> list(String query, Object[] params) throws AcademyException{ //passo una lista di oggetti params
		try {
			PreparedStatement cmd = SQLConfiguration.getInstance().getConnection().prepareStatement(query); // compile query

			cmd = createSet(cmd, params); //load parameters into query object	
			ResultSet res = cmd.executeQuery();

			return resultsetToList(res);

		} catch (Exception e) {
			throw new AcademyException(e.getMessage());
		}


	}

	/*
	 * Load parameters into PreparedStatement
	 */

	private PreparedStatement createSet (PreparedStatement cmd, Object [] params) throws SQLException {
		int idx = 1;
		for (Object o:params) {
			cmd.setObject(idx++, o);
		}
		return cmd;
	}

	/*
	 * Transform result set into map List 
	 */
	private List<Map<String, Object>> resultsetToList(ResultSet rs) throws SQLException{
		ResultSetMetaData md = rs.getMetaData();  // retrieve resultSet MetaData
		int colums = md.getColumnCount();         // retrieve query columns number

		List<Map<String, Object>> rows = new ArrayList<Map<String,Object>>(); // prepare result

		while(rs.next()) {
			Map<String, Object> row = new HashMap<String, Object>();
			for (int i=1; i <= colums;i++) {
				row.put(md.getColumnName(i), rs.getObject(i));
			}
			rows.add(row);
		}

		return rows;		
	}
	/*
	 * Transform resultSet in map
	 */
	private Map<String, Object> resultsetToMap(ResultSet rs) throws SQLException{
		ResultSetMetaData md = rs.getMetaData();  // retrieve resultSet MetaData
		int colums = md.getColumnCount();         // retrieve query columns number

		if(!rs.next()) return null; //no result



		Map<String, Object> row = new HashMap<String, Object>();
		for (int i=1; i <= colums;i++) {
			row.put(md.getColumnName(i), rs.getObject(i));
		}

		return row;	
	}
}