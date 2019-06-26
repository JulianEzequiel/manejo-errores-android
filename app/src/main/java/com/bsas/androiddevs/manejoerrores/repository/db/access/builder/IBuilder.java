package com.bsas.androiddevs.manejoerrores.repository.db.access.builder;

import android.content.ContentValues;
import android.database.Cursor;

import com.bsas.androiddevs.manejoerrores.repository.db.entity.DbEntity;

public interface IBuilder<T extends DbEntity> {

    T build(Cursor cursor) throws Exception;

    T build(Cursor cursor, T entity) throws Exception;

    ContentValues createValues(T entity) throws Exception;

    String[] getFieldsNames();

}

