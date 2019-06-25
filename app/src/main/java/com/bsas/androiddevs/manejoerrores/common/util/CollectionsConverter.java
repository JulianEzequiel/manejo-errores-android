package com.bsas.androiddevs.manejoerrores.common.util;

import com.bsas.androiddevs.manejoerrores.common.Movie;
import com.bsas.androiddevs.manejoerrores.repository.api.dto.MovieDto;
import com.bsas.androiddevs.manejoerrores.repository.db.entity.MovieDb;

import java.util.ArrayList;
import java.util.List;

public class CollectionsConverter {

    public static List<Movie> movieDbsToMovies(List<MovieDb> moviesDb) {
        if (moviesDb != null && !moviesDb.isEmpty()) {
            List<Movie> movies = new ArrayList<>();
            for (MovieDb movieDb : moviesDb) {
                movies.add(Movie.createFromMovieDb(movieDb));
            }
            return movies;
        }
        return null;
    }

    public static List<MovieDb> movieDtosToMovieDbs(List<MovieDto> movieDtos) {
        if (movieDtos != null && !movieDtos.isEmpty()) {
            List<MovieDb> movies = new ArrayList<>();
            for (MovieDto movieDto : movieDtos) {
                movies.add(MovieDb.createFromMovieDto(movieDto));
            }
            return movies;
        }
        return null;
    }
}
