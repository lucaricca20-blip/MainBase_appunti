package com.betacom.utils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.betacom.exceptions.AcademyException;
import com.betacom.singletone.SQLConfiguration;

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
	
	
}