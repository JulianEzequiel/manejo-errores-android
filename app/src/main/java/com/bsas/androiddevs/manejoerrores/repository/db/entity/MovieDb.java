package com.bsas.androiddevs.manejoerrores.repository.db.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

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

    public MovieDb(@NonNull String id, String title, int year, Date creationDate) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.creationDate = creationDate;
    }

    public static MovieDb createFromMovieDto(MovieDto movieDto) {
        return new MovieDb(movieDto.getImdbId(), movieDto.getTitle(), movieDto.getYear(), new Date());
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
