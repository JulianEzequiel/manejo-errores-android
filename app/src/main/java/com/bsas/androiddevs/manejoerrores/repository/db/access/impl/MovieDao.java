package com.bsas.androiddevs.manejoerrores.repository.db.access.impl;

import android.content.Context;
import android.database.Cursor;

import com.bsas.androiddevs.manejoerrores.common.exception.DbException;
import com.bsas.androiddevs.manejoerrores.common.exception.ExceptionLogger;
import com.bsas.androiddevs.manejoerrores.repository.db.access.SingleDataAccess;
import com.bsas.androiddevs.manejoerrores.repository.db.access.builder.MovieDbBuilder;
import com.bsas.androiddevs.manejoerrores.repository.db.entity.MovieDb;

public class MovieDao extends SingleDataAccess<MovieDb> {

    private static final String TABLE_NAME = "MOVIES";

    public MovieDao(Context context) {
        super(context, new MovieDbBuilder());
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected String getIdWhereClause(MovieDb entity) {
        return String.format("%s = '%s'", MovieDbBuilder.ID, entity.getId());
    }

    @Override
    protected String getIdWhereClauseForId(Object id) {
        return String.format("%s = '%s'", MovieDbBuilder.ID, id.toString());
    }

    public int countAll() throws DbException {
        int quantity = 0;
        String query = "SELECT COUNT(*) quantity FROM " + TABLE_NAME;

        Cursor cursor = null;
        try {
            if (this.database == null) {
                this.openDB();
            }

            cursor = this.database.rawQuery(query, null);
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    quantity = cursor.getInt(cursor.getColumnIndex("quantity"));
                }
                cursor.close();
            }
        } catch (Exception e) {
            ExceptionLogger.error(e);
            throw new DbException(e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            this.closeDB();
        }

        return quantity;
    }
}
