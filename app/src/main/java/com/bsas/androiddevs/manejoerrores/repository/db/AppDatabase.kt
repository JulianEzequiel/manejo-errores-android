package com.bsas.androiddevs.manejoerrores.repository.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bsas.androiddevs.manejoerrores.repository.db.converter.DatabaseConverters
import com.bsas.androiddevs.manejoerrores.repository.db.dao.MovieDao
import com.bsas.androiddevs.manejoerrores.repository.db.entity.MovieDb

val DB_NAME = "AppDatabase"

@Database(entities = [MovieDb::class], version = 1, exportSchema = false)
@TypeConverters(DatabaseConverters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract val movieDao: MovieDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun instance(): AppDatabase {
            return INSTANCE ?: throw RuntimeException("Database not configured")
        }

        fun configure(context: Context) {
            synchronized(this) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            AppDatabase::class.java,
                            DB_NAME)
                            .build()
                }
            }
        }

    }

}
