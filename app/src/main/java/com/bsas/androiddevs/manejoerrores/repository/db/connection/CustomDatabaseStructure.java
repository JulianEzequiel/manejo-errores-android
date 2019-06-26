package com.bsas.androiddevs.manejoerrores.repository.db.connection;

import java.util.ArrayList;

public class CustomDatabaseStructure extends DatabaseStructure {
    public CustomDatabaseStructure() {
        super();
        this.setTables(new ArrayList<String>());
        this.getTables().add("CREATE TABLE MOVIES (id TEXT PRIMARY KEY, title TEXT, year INTEGER, creationDate INTEGER);");
    }
}
