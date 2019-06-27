package com.bsas.androiddevs.manejoerrores.repository.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bsas.androiddevs.manejoerrores.repository.api.dto.MovieDto
import java.util.*

@Entity(tableName = "MOVIES")
data class MovieDb(@PrimaryKey @ColumnInfo(name = "id") var id: String,
                   @ColumnInfo(name = "title") var title: String,
                   @ColumnInfo(name = "year") var year: Int,
                   @ColumnInfo(name = "creationDate") var creationDate: Date = Date()) {

    constructor(movieDto: MovieDto) : this(movieDto.imdbId, movieDto.title, movieDto.year)

}