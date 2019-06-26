package com.bsas.androiddevs.manejoerrores.repository.db.entity;

import java.io.Serializable;

public interface DbEntity extends Serializable, Comparable<DbEntity> {

    Object getId();

}
