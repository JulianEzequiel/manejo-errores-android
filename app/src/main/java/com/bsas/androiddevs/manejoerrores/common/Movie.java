package com.bsas.androiddevs.manejoerrores.common;

import com.bsas.androiddevs.manejoerrores.repository.db.entity.MovieDb;

public class Movie {

    private String title;
    private int year;

    public Movie(String title, int year) {
        this.title = title;
        this.year = year;
    }

    public static Movie createFromMovieDb(MovieDb movieDb) {
        return new Movie(movieDb.getTitle(), movieDb.getYear());
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }
}
