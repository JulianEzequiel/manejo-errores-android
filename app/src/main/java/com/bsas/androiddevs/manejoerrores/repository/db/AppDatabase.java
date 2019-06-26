package com.bsas.androiddevs.manejoerrores.repository.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.bsas.androiddevs.manejoerrores.repository.db.converter.DateDbConverter;
import com.bsas.androiddevs.manejoerrores.repository.db.dao.MovieDao;
import com.bsas.androiddevs.manejoerrores.repository.db.entity.MovieDb;

@Database(entities = {MovieDb.class},
        version = 1,
        exportSchema = false)
@TypeConverters({DateDbConverter.class})
public abstract class AppDatabase extends RoomDatabase {

    private static final String DB_NAME = "AppDatabase";

    private static AppDatabase selfInstance;

    public static void configure(Context context) {
        if (selfInstance == null) {
            selfInstance = Room
                    .databaseBuilder(context, AppDatabase.class, DB_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
    }

    public static synchronized AppDatabase getInstance() {
        if (selfInstance == null) {
            throw new RuntimeException("DB Not Configured");
        }
        return selfInstance;
    }

    public abstract MovieDao movieDao();


}
