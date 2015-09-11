package ru.scada.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T, ID extends Serializable> {
    List<T> showHoursData(int startIndex, int pageSize);
    List<T> showDayData();

    Long getCount();
}
