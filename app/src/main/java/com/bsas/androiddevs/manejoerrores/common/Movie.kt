package com.bsas.androiddevs.manejoerrores.common

import com.bsas.androiddevs.manejoerrores.repository.db.entity.MovieDb

data class Movie (var title : String,
                  var year : Int) {
    constructor(movieDb: MovieDb) : this(movieDb.title, movieDb.year)
}
