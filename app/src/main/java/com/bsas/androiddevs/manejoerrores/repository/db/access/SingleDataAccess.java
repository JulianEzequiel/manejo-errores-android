package com.bsas.androiddevs.manejoerrores.repository.db.access;

import android.content.Context;
import android.database.Cursor;

import com.bsas.androiddevs.manejoerrores.common.exception.DbException;
import com.bsas.androiddevs.manejoerrores.common.exception.ExceptionLogger;
import com.bsas.androiddevs.manejoerrores.repository.db.access.builder.IBuilder;
import com.bsas.androiddevs.manejoerrores.repository.db.entity.DbEntity;

import java.util.ArrayList;
import java.util.List;

public abstract class SingleDataAccess<T extends DbEntity> extends DataAccess<T> implements ISingleDataAccess<T> {

    public SingleDataAccess(Context context, IBuilder<T> builder) {
        super(context, builder);
    }

    public SingleDataAccess(BaseDataAccess parent, IBuilder<T> builder) {
        super(parent, builder);
    }

    @Override
    public void create(T entity) throws DbException {
        if (this.database == null) {
            this.openDB();
        }
        long rowId = -1;
        try {
            rowId = this.database.insert(this.getTableName(), null, this.builder.createValues(entity));
            if (rowId < 0) {
                throw new Exception("Error while inserting register");
            }
        } catch (Exception e) {
            ExceptionLogger.error(e);
            throw new DbException(e);
        } finally {
            this.releaseResourcesOnFinally();
        }
    }

    @Override
    public void createAll(List<T> entities) throws DbException {
        if (this.database == null) {
            this.openDB();
        }
        try {
            String tableName = this.getTableName();
            for (T entity : entities) {
                long rowId = this.database.insert(tableName, null, this.builder.createValues(entity));
                if (rowId < 0) {
                    throw new Exception("Error while inserting regiser");
                }
            }
        } catch (Exception e) {
            ExceptionLogger.error(e);
            throw new DbException(e);
        } finally {
            this.releaseResourcesOnFinally();
        }
    }

    @Override
    public void update(T entity) throws DbException {
        if (this.database == null) {
            this.openDB();
        }
        try {
            this.database.update(this.getTableName(), this.builder.createValues(entity), this.getIdWhereClause(entity), null);
        } catch (Exception e) {
            ExceptionLogger.error(e);
            throw new DbException(e);
        } finally {
            this.releaseResourcesOnFinally();
        }
    }

    @Override
    public List<T> findAll() throws DbException {
        List<T> result = null;
        if (this.database == null) {
            this.openDB();
        }
        Cursor cursor = null;
        try {
            cursor = this.database.query(true, this.getTableName(), this.builder.getFieldsNames(), null, null, null, null, null, null);
            if (cursor != null) {
                result = new ArrayList<T>();
                while (cursor.moveToNext()) {
                    T entity = this.builder.build(cursor);
                    if (entity != null) {
                        result.add(entity);
                    }
                }
            }
        } catch (Exception e) {
            ExceptionLogger.error(e);
            throw new DbException(e);
        } finally {
            this.releaseResourcesOnFinally(cursor);
        }
        return result;
    }

    protected void releaseResourcesOnFinally(Cursor... cursors) {
        if (cursors != null && cursors.length > 0) {
            for (Cursor cursor : cursors) {
                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                }
            }
        }

        if (this.database != null && !this.database.inTransaction()) {
            this.closeDB();
        }
    }

    protected abstract String getTableName();

    protected abstract String getIdWhereClause(T entity);

    protected abstract String getIdWhereClauseForId(Object id);

    protected String getParentIdWhereClauseForId(Object parentId) {
        return null;
    }

    protected String getKeyFieldWhereClause() {
        return null;
    }
}

