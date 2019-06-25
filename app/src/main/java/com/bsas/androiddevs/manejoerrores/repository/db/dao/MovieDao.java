package com.bsas.androiddevs.manejoerrores.repository.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.bsas.androiddevs.manejoerrores.repository.db.entity.MovieDb;

import java.util.List;

@Dao
public interface MovieDao extends BaseDao<MovieDb> {

    @Query("SELECT COUNT(*) FROM MOVIES")
    int countAll();

    @Query("SELECT * FROM MOVIES")
    List<MovieDb> findAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveAll(List<MovieDb> movieDbs);
}
