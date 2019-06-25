package com.bsas.androiddevs.manejoerrores.repository.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;

@Dao
public interface BaseDao<T> {

    @Delete
    void delete(T entity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void upsert(T entity);

}