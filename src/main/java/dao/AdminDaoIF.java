package dao;

import java.io.Serializable;

public interface AdminDaoIF<T, ID extends Serializable> {
    T findRegByValue(Integer value) throws Exception;

    T findDataByValue(Integer value) throws Exception;
}
