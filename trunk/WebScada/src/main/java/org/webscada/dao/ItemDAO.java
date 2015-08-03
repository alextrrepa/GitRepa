package org.webscada.dao;

import java.io.Serializable;

public interface ItemDAO<T, ID extends Serializable> extends GenericDao<T, ID> {
    T findRegByValue(Integer value);
    T findDataByValue(Integer value);
}
