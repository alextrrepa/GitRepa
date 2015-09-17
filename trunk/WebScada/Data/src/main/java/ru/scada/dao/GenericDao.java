package ru.scada.dao;

import java.util.Date;
import java.util.List;

public interface GenericDao<T> {
    List<T> showData(Date minDtime, Date maxDtime);
    List<T> showDayData();
    Long getCount();
}
