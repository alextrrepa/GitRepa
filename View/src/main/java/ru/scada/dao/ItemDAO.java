package ru.scada.dao;

import java.io.Serializable;
import java.util.List;

public interface ItemDAO<T, ID extends Serializable> extends GenericDao<T, ID> {
    T findRegByValue(Integer value);
    T findDataByValue(Integer value);
    List<T> getTags();
}
