package dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T, ID extends Serializable> {
    List<T> getAllConfig() throws Exception;
    T getById(ID id) throws Exception;
    void create(T entity) throws Exception;
    void delete(ID id) throws Exception;
    void update(T entity) throws Exception;
}
