package org.webscada.dao;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractDao<T, ID extends Serializable> {
    private Class<T> persistanceClass;

    public AbstractDao(Class<T> persistanceClass) {
        this.persistanceClass = persistanceClass;
    }

    public Class<T> getPersistanceClass() {
        return persistanceClass;
    }

    public abstract List<T> getAllConfig();
    public abstract T getById(ID id);
    public abstract void create(T entity);
    public abstract void delete(ID id);
    public abstract void update(T entity);
}
