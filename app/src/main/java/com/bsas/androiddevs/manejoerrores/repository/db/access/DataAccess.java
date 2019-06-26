package com.bsas.androiddevs.manejoerrores.repository.db.access;

import android.content.Context;
import android.database.Cursor;

import com.bsas.androiddevs.manejoerrores.common.exception.DbException;
import com.bsas.androiddevs.manejoerrores.common.exception.ExceptionLogger;
import com.bsas.androiddevs.manejoerrores.repository.db.access.builder.IBuilder;
import com.bsas.androiddevs.manejoerrores.repository.db.entity.DbEntity;

import java.util.ArrayList;
import java.util.Collection;

public abstract class DataAccess<T extends DbEntity> extends BaseDataAccess {

    protected IBuilder<T> builder = null;

    public DataAccess(Context context, IBuilder<T> builder) {
        super(context);
        this.builder = builder;
    }

    public DataAccess(BaseDataAccess parent, IBuilder<T> builder) {
        super(parent.context);
        this.builder = builder;
        this.database = parent.database;
        this.IsInConnectionContext = parent.IsInConnectionContext;
    }

    protected Collection<DbEntity> queryFull(String query, String[] arguments) throws DbException {
        Collection<DbEntity> entities = null;

        if (this.database == null) {
            this.openDB();
        }
        Cursor cursor = null;
        try {
            cursor = this.database.rawQuery(query, arguments);
            if (cursor != null) {
                entities = new ArrayList<DbEntity>();
                while (cursor.moveToNext()) {
                    DbEntity entity = this.builder.build(cursor);
                    if (entity != null) {
                        entities.add(entity);
                    }
                }
            }
        } catch (Exception e) {
            ExceptionLogger.error(e);
            throw new DbException(e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (this.database != null && !this.database.inTransaction()) {
                this.closeDB();
            }
        }

        return entities;
    }
}

