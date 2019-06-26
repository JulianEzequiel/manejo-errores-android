package com.bsas.androiddevs.manejoerrores.repository.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.bsas.androiddevs.manejoerrores.repository.db.entity.MovieDb;

import java.util.List;

@Dao
public interface BaseDao<T> {

    @Delete
    void delete(T entity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void upsert(T entity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void upsertAll(List<MovieDb> movieDbs);

}