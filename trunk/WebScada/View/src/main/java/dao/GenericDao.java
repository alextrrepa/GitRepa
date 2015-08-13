package dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T, ID extends Serializable> {
    List<T> getAllConfig();
    T getById(ID id);
    void create(T entity);
    void delete(ID id);
    void update(T entity);
}
