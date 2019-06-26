package com.bsas.androiddevs.manejoerrores.repository.db.access;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.bsas.androiddevs.manejoerrores.repository.db.connection.DatabaseInteractor;

public class BaseDataAccess {

    protected Context context = null;
    protected DatabaseInteractor databaseHelper = null;
    protected SQLiteDatabase database = null;
    protected Boolean IsInConnectionContext = false;

    public BaseDataAccess(Context context){
        this.context = context;
        this.databaseHelper = new DatabaseInteractor(context);
    }

    public void startConnection() throws SQLException {
        this.IsInConnectionContext = true;
        if(this.database == null || !this.database.isOpen()) {
            this.database = this.databaseHelper.getWritableDatabase();
        }
    }

    public void closeConnection(){
        this.IsInConnectionContext = false;
        this.databaseHelper.close();
        if(this.database != null) {
            this.database.close();
        }
        this.database = null;
    }

    protected void openDB() throws SQLException {
        if(this.IsInConnectionContext) {
            return;
        }
        if(this.database == null || !this.database.isOpen()) {
            this.database = this.databaseHelper.getWritableDatabase();
        }
    }

    protected void closeDB(){
        if(this.IsInConnectionContext) {
            return;
        }
        this.databaseHelper.close();
        if(this.database != null) {
            this.database.close();
        }
        this.database = null;
    }

    public void beginTransaction(){
        if(database==null){
            this.openDB();
        }
        if(!this.database.inTransaction()){
            this.database.beginTransaction();
        }
    }

    public void commitTransaction(){
        if(this.database.inTransaction()){
            this.database.setTransactionSuccessful();
            this.database.endTransaction();
            this.closeDB();
        }
    }

    public void rollbackTransaction(){
        if(this.database.inTransaction()){
            this.database.endTransaction();
            this.closeDB();
        }
    }
}

