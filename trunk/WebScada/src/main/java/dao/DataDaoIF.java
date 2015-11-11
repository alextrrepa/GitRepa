package dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public interface DataDaoIF<T, ID extends Serializable> {
    List<T> getDataBetweenDates(Date startDate, Date endDate);

}