package ru.scada.dao;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.scada.util.SessionUtil;

import java.io.Serializable;
import java.util.List;

public class ItemDao<T, ID extends Serializable> implements GenericDao<T, ID> {
    private final static Logger log = Logger.getLogger(ItemDao.class);

    @Override
    public List<T> showHoursData() {
        Session session = SessionUtil.getSession();
        List<T> result = null;
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from HourEntity");
            result = query.list();
            transaction.commit();
        } catch (Exception e) {
            try {
                transaction.rollback();
            } catch (Exception ex) {
                log.error("Rollback transaction error", ex);
            }
        }
        return result;
    }

    @Override
    public List<T> showDayData() {
        return null;
    }
}
