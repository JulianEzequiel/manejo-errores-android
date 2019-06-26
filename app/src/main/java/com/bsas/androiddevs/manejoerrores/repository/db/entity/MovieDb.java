package com.bsas.androiddevs.manejoerrores.repository.db.entity;

import android.support.annotation.NonNull;

import com.bsas.androiddevs.manejoerrores.repository.api.dto.MovieDto;

import java.util.Date;

public class MovieDb implements DbEntity {

    private String id;

    private String title;
    private int year;

    private Date creationDate;

    public static MovieDb createFromMovieDto(MovieDto movieDto) {
        MovieDb movieDb = new MovieDb();
        movieDb.setId(movieDto.getImdbId());
        movieDb.setTitle(movieDto.getTitle());
        movieDb.setYear(movieDto.getYear());
        movieDb.setCreationDate(new Date());
        return movieDb;
    }

    @Override
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public int compareTo(@NonNull DbEntity dbEntity) {
        return 0;
    }
}
