package com.bsas.androiddevs.manejoerrores.repository.db.connection;

import java.io.Reader;
import java.util.Collection;
import java.util.Map;

public class DatabaseStructure {
	
	private static DatabaseStructure instance = null;

	private Collection<String> tables;
	private Map<Integer, Reader> updates;
	
	public static DatabaseStructure getInstance(){
		if(instance==null){
			instance = new CustomDatabaseStructure();
		}
		return instance;
	}
	
	/**
	 * @return the tables
	 */
	public Collection<String> getTables() {
		return tables;
	}
	/**
	 * @param tables the tables to set
	 */
	public void setTables(Collection<String> tables) {
		this.tables = tables;
	}
	/**
	 * @return the updates
	 */
	public Map<Integer, Reader> getUpdates() {
		return updates;
	}
	/**
	 * @param updates the updates to set
	 */
	public void setUpdates(Map<Integer, Reader> updates) {
		this.updates = updates;
	}	
}
