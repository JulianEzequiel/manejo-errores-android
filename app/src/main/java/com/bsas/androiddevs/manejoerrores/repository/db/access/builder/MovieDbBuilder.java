package com.bsas.androiddevs.manejoerrores.repository.db.access.builder;

import android.content.ContentValues;
import android.database.Cursor;

import com.bsas.androiddevs.manejoerrores.repository.db.entity.MovieDb;

import java.util.Date;

public class MovieDbBuilder implements IBuilder<MovieDb> {

    public static final String ID = "id";
    public static final String TITLE = "title";
    public static final String YEAR = "year";
    public static final String CREATION_DATE = "creationDate";

    @Override
    public MovieDb build(Cursor cursor) throws Exception {
        MovieDb movieDb = new MovieDb();
        movieDb = this.build(cursor, movieDb);
        return movieDb;
    }

    @Override
    public MovieDb build(Cursor cursor, MovieDb entity) throws Exception {
        entity.setId(cursor.getString(cursor.getColumnIndex(ID)));
        entity.setTitle(cursor.getString(cursor.getColumnIndex(TITLE)));
        entity.setYear(cursor.getInt(cursor.getColumnIndex(YEAR)));

        long millis = cursor.getLong(cursor.getColumnIndex(CREATION_DATE));
        entity.setCreationDate(new Date(millis));

        return entity;
    }

    @Override
    public ContentValues createValues(MovieDb entity) throws Exception {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID, entity.getId());
        contentValues.put(TITLE, entity.getTitle());
        contentValues.put(YEAR, entity.getYear());
        contentValues.put(CREATION_DATE, entity.getCreationDate().getTime());
        return contentValues;
    }

    @Override
    public String[] getFieldsNames() {
        return new String[]{ID, TITLE, YEAR, CREATION_DATE};
    }
}
