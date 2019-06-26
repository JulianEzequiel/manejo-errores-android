package com.bsas.androiddevs.manejoerrores.repository.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.bsas.androiddevs.manejoerrores.repository.db.entity.MovieDb

@Dao
interface MovieDao : BaseDao<MovieDb> {

    @Query("SELECT COUNT(*) FROM MOVIES")
    suspend fun countAll(): Int

    @Query("SELECT * FROM MOVIES")
    suspend fun findAll(): List<MovieDb>

}