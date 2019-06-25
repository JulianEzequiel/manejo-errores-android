package com.bsas.androiddevs.manejoerrores.repository.db.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.bsas.androiddevs.manejoerrores.repository.api.dto.MovieDto;

import java.util.Date;

@Entity(tableName = "MOVIES")
public class MovieDb {

    @PrimaryKey
    @NonNull
    private String id;

    private String title;
    private int year;

    private Date creationDate;

    private MovieDb() {
    }

    public MovieDb(@NonNull String id, String title, int year) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.creationDate = new Date();
    }

    public static MovieDb createFromMovieDto(MovieDto movieDto) {
        return new MovieDb(movieDto.getImdbId(), movieDto.getTitle(),
                movieDto.getYear());
    }

    @NonNull
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

}
