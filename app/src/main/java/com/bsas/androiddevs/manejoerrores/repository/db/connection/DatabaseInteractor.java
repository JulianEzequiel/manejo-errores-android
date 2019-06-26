package com.bsas.androiddevs.manejoerrores.repository.db.connection;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.bsas.androiddevs.manejoerrores.common.exception.ExceptionLogger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;

public class DatabaseInteractor extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "AppDatabase.db";
    private static final String DATABASE_BACKUP_EXTENSION = ".bkp";

    private static int databaseVersion = 1;

    private Collection<Integer> updatesExecuted = null;

    public DatabaseInteractor(Context context) {
        super(context, DATABASE_NAME, null, databaseVersion);
    }

    /**
     * @return the databaseVersion
     */
    public static int getDatabaseVersion() {
        return databaseVersion;
    }

    /**
     * @param databaseVersion the databaseVersion to set
     */
    public static void setDatabaseVersion(int databaseVersion) {
        DatabaseInteractor.databaseVersion = databaseVersion;
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        // Creates all database structure
        if(DatabaseStructure.getInstance().getTables()!=null && DatabaseStructure.getInstance().getTables().size()>0){
            for (String table : DatabaseStructure.getInstance().getTables()) {
                database.execSQL(table);
            }
        }

        if(DatabaseStructure.getInstance().getUpdates()!=null && DatabaseStructure.getInstance().getUpdates().size()>0){
            for(int version = database.getVersion() + 1; version <= databaseVersion; version++){
                BufferedReader reader = null;
                try {
                    reader = new BufferedReader(DatabaseStructure.getInstance().getUpdates().get(Integer.valueOf(version)));
                    if(reader.ready()){
                        String script = reader.readLine();
                        while (script!=null) {
                            database.execSQL(script);
                            script = reader.readLine();
                        }
                    }
                } catch (Exception e) {
                    ExceptionLogger.error(e);
                    Log.e("onCreate()", "Unable to update database", e);
                } finally{
                    try {
                        if(reader!=null){
                            reader.close();
                        }
                    } catch (Exception e2) {
                        ExceptionLogger.error(e2);
                        Log.e("onCreate()", "Error closing reader", e2);
                    }
                }
            }
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        if(DatabaseStructure.getInstance().getUpdates()!=null && DatabaseStructure.getInstance().getUpdates().size()>0){
            for (int version = oldVersion + 1; version <= newVersion; version++) {
                BufferedReader reader = null;
                try {
                    reader = new BufferedReader(DatabaseStructure.getInstance().getUpdates().get(Integer.valueOf(version)));
                    if(reader.ready()){
                        String script = reader.readLine();
                        while (script!=null) {
                            database.execSQL(script);
                            script = reader.readLine();
                        }
                    }
                    this.addExecutedUpdate(version);
                } catch (Exception e) {
                    ExceptionLogger.error(e);
                    Log.e("onCreate()", "Unable to update database", e);
                } finally{
                    try {
                        if(reader!=null){
                            reader.close();
                        }
                    } catch (Exception e2) {
                        ExceptionLogger.error(e2);
                        Log.e("onCreate()", "Error closing reader", e2);
                    }
                }
            }
        }
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Nada
    }

    @Deprecated
    /**
     * Android documentation promote the use of transactions directly.. so this method is no longer needed
     */
    public boolean backupDatabase(){
        InputStream input = null;
        OutputStream output = null;
        boolean succed = false;
        try {
            SQLiteDatabase database = this.getWritableDatabase();
            String databasePath = database.getPath();
            database.close();
            File db = new File(databasePath);
            // Local database
            input = new FileInputStream(db);

            // create directory for backup
            File backup = new File(String.format("%s%s", databasePath, DATABASE_BACKUP_EXTENSION));

            // Path to the external backup
            output = new FileOutputStream(backup);

            // transfer bytes from the Input File to the Output File
            byte[] buffer = new byte[1024];
            int length;
            while ((length = input.read(buffer))>0) {
                output.write(buffer, 0, length);
            }

            output.flush();
            output.close();
            input.close();
            succed = true;

        } catch (Exception e) {
            ExceptionLogger.error(e);
            succed = false;
        }
        return succed;
    }

    @Deprecated
    /**
     * Android documentation promote the use of transactions directly.. so this method is no longer needed
     */
    public boolean restoreDatabase(){
        InputStream input = null;
        OutputStream output = null;
        boolean succed = false;
        try {
            SQLiteDatabase database = this.getWritableDatabase();
            String databasePath = database.getPath();
            database.close();
            File db = new File(databasePath);
            if(db.delete()){
                File backup = new File(String.format("%s%s", databasePath, DATABASE_BACKUP_EXTENSION));

                // Local database
                input = new FileInputStream(backup);

                // Path to the external backup
                output = new FileOutputStream(db);

                // transfer bytes from the Input File to the Output File
                byte[] buffer = new byte[1024];
                int length;
                while ((length = input.read(buffer))>0) {
                    output.write(buffer, 0, length);
                }

                output.flush();
                output.close();
                input.close();
                succed = true;
            }

        } catch (Exception e) {
            ExceptionLogger.error(e);
            succed = false;
        }
        return succed;
    }

    public boolean removeDatabaseBackup() throws Exception {
        boolean succed = false;
        SQLiteDatabase database = this.getWritableDatabase();
        String databasePath = database.getPath();
        database.close();
        File backup = new File(String.format("%s%s", databasePath, DATABASE_BACKUP_EXTENSION));
        if (backup.delete()) {
            succed = true;
        }
        return succed;
    }

    private void addExecutedUpdate(int updateNumber){
        if(this.updatesExecuted==null){
            this.updatesExecuted = new ArrayList<Integer>();
        }
        this.updatesExecuted.add(new Integer(updateNumber));
    }

    public Collection<Integer> getUpdatesExecuted(){
        return this.updatesExecuted;
    }
}
