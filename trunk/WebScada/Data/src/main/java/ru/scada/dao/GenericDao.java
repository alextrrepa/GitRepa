package ru.scada.dao;

import java.util.List;

public interface GenericDao<T> {
    List<T> showHoursData(/*int startPageIndex, int numRecordsPerPage*/String minDtime, String maxDtime);
    List<T> showDayData();

    Long getCount();
}
