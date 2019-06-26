package com.bsas.androiddevs.manejoerrores.repository.db.access;

import com.bsas.androiddevs.manejoerrores.common.exception.DbException;
import com.bsas.androiddevs.manejoerrores.repository.db.entity.DbEntity;

import java.util.List;

/**
 * Created by Quares Dev User on 29/09/2015.
 */
public interface ISingleDataAccess <T extends DbEntity> {

    void beginTransaction();

    void commitTransaction();

    void rollbackTransaction();

    void create(T entity) throws DbException;

    void update(T entity) throws DbException;

    List<T> findAll() throws DbException;

    void createAll(List<T> entities) throws DbException;

}

