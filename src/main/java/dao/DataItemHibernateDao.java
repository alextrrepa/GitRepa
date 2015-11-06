package dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class DataItemHibernateDao<T, ID extends Serializable> extends CommonOperationsHibernateDao<T, ID>
        implements DataDaoIF<T, ID> {
    public DataItemHibernateDao(Class<T> persistenceClass) {
        super(persistenceClass);
    }

    @Override
    public List<T> getAllDataBetweenDates(Date startDate, Date endDate) {

        return null;
    }
}
