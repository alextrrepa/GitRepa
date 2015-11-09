package dao;

import entities.TagEntity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public interface DataDaoIF<T, ID extends Serializable> {
    List<TagEntity> getDataBetweenDates(Date startDate, Date endDate);
}