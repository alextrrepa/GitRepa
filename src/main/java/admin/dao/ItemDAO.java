package admin.dao;

import java.io.Serializable;

public interface ItemDAO<T, ID extends Serializable> extends GenericDao<T, ID> {
    T findRegByValue(Integer value) throws Exception;

    T findDataByValue(Integer value) throws Exception;
}
