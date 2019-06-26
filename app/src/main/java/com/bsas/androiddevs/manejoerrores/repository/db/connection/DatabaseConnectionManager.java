package com.bsas.androiddevs.manejoerrores.repository.db.connection;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseConnectionManager {

    private static DatabaseConnectionManager instance = null;

    protected Context context = null;
    protected DatabaseInteractor databaseHelper = null;
    protected SQLiteDatabase database = null;
    protected boolean inTransaction = false;

    private DatabaseConnectionManager() {
        super();
    }

    public static DatabaseConnectionManager getInstance() {
        if (DatabaseConnectionManager.instance == null) {
            DatabaseConnectionManager.instance = new DatabaseConnectionManager();

        }
        return DatabaseConnectionManager.instance;
    }

    public synchronized void configure(Context context) {
        this.context = context;
        if (this.databaseHelper == null) {
            this.databaseHelper = new DatabaseInteractor(context);
        }
    }

    public synchronized void beginTransaction() {
        this.inTransaction = true;
        if (this.database == null) {
            this.database = this.databaseHelper.getWritableDatabase();
        }
        this.database.beginTransaction();
    }

    public synchronized void commitTransaction() {
        this.inTransaction = false;
        if (this.database.inTransaction()) {
            this.database.setTransactionSuccessful();
            this.database.endTransaction();
        }
        this.closeDB();
    }

    public synchronized void rollbackTransaction() {
        this.inTransaction = false;
        if (this.database.inTransaction()) {
            this.database.endTransaction();
        }
        this.closeDB();
    }

    public synchronized SQLiteDatabase getDatabase() {
        if (this.database == null) {
            this.openDB();
        }
        return this.database;
    }

    public synchronized void openDB() throws SQLException {
        if (this.database == null) {
            this.database = this.databaseHelper.getWritableDatabase();
        }
    }

    public synchronized void closeDB() {
        if (!this.inTransaction && this.databaseHelper != null) {
            this.databaseHelper.close();
            this.database = null;
        }
    }

}
