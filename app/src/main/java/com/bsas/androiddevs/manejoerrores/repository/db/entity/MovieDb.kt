package com.bsas.androiddevs.manejoerrores.repository.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bsas.androiddevs.manejoerrores.repository.api.dto.MovieDto
import java.util.*

@Entity(tableName = "MOVIES")
data class MovieDb(@PrimaryKey
                   var id: String,
                   var title: String,
                   var year: Int,
                   var creationDate: Date = Date()) {
    constructor(movieDto: MovieDto) : this(movieDto.imdbId, movieDto.title, movieDto.year)
}