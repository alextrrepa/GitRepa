package dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public interface DataDaoIF<T, ID extends Serializable> {
    List<T> getAllDataBetweenDates(Date startDate, Date endDate);
}