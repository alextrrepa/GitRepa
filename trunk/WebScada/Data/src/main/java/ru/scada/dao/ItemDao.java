package ru.scada.dao;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import ru.scada.util.SessionUtil;

import java.util.List;

public class ItemDao<T> implements GenericDao {
    private final static Logger log = Logger.getLogger(ItemDao.class);
    private Class<T> persistanceClass;

    public ItemDao(Class<T> persistanceClass) {
        this.persistanceClass = persistanceClass;
    }

    public Class<T> getPersistanceClass() {
        return persistanceClass;
    }

    @Override
    public List<T> showHoursData(/*int startIndex, int pageSize*/String minDtime, String maxDtime) {
        Session session = SessionUtil.getSession();
        List<T> result = null;
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(getPersistanceClass());
//                    .add(Restrictions.between("dtime", minDtime, maxDtime));
//                    .addOrder(Order.asc("tag_id"))
//                    .addOrder(Order.asc("dtime"));
//
            result = criteria.list();
//            query.setFirstResult(startIndex);
//            query.setMaxResults(pageSize);
//            result = query.list();
            transaction.commit();
        } catch (Exception e) {
            try {
                transaction.rollback();
            } catch (Exception ex) {
                log.error("Rollback transaction error", ex);
            }
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public List showDayData() {
        return null;
    }

    @Override
    public Long getCount() {
        Session session = SessionUtil.getSession();
        Transaction transaction = null;
        Long count = null;
        try {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(getPersistanceClass());
            criteria.setProjection(Projections.rowCount());
            count = (Long) criteria.uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            try {
                transaction.rollback();
            } catch (Exception ex) {
                log.error("Rollback transaction error", ex);
            }
        } finally {
            session.close();
        }
        return count;
    }
}