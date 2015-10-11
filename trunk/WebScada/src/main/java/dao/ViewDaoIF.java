package dao;

import java.io.Serializable;
import java.util.List;

public interface ViewDaoIF<T, ID extends Serializable> {
    List<T> getTags();
}
