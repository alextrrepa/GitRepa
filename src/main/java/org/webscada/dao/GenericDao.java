package org.webscada.dao;


import java.io.Serializable;
import java.util.List;

public interface GenericDao<T, ID extends Serializable> {
    public  List<T> getAllConfig();
    public T getById(ID id);
    public void create(T entity);
    public void delete(ID id);
    public void update(T entity);
}
