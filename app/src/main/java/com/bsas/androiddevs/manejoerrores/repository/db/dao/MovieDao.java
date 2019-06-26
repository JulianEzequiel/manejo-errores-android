package com.bsas.androiddevs.manejoerrores.repository.db.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.bsas.androiddevs.manejoerrores.repository.db.entity.MovieDb;

import java.util.List;

@Dao
public interface MovieDao extends BaseDao<MovieDb> {

    @Query("SELECT COUNT(*) FROM MOVIES")
    int countAll();

    @Query("SELECT * FROM MOVIES")
    List<MovieDb> findAll();
}
