package ru.scada.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T, ID extends Serializable> {
    List<T> showHoursData();
    List<T> showDayData();

    Long getCount();
}
