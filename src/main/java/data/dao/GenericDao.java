package data.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface GenericDao<T> {
    Map<String, List<T>> showData(Date minDtime, Date maxDtime);

    List<T> showDayData();

    Long getCount();
}
