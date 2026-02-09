package com.betacom.process;
import com.betacom.services.ServicesQuery;
import com.betacom.services.ServicesUpdate;
import com.betacom.singletone.SQLConfiguration;

import interfaces.ProcessInterface;

public class ProcessSQL implements ProcessInterface{

	@Override
	public boolean execute() throws Exception {
		System.out.println("Begin process SQL");
		
		try {
			SQLConfiguration.getInstance().getConnection();
			System.out.println("Connection with db ok");
			
			// new ServicesQuery().executeQuery();  // riattivalo se vuoi vedere metodi col db
			new ServicesUpdate().executeUpdate();
			
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		
		
		return false;
	}

}