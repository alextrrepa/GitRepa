package ru.scada.dao;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import ru.scada.model.HourEntity;
import ru.scada.util.SessionUtil;

import java.util.Date;
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
    public List<T> showData(Date sDtime, Date enDtime) {
        Session session = SessionUtil.getSession();
        List<T> result = null;
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(getPersistanceClass())
                    .add(Restrictions.between("dtime", sDtime, enDtime))
                    .addOrder(Order.asc("tagEntity.id"));
            result = criteria.list();
/*            for (HourEntity h : result) {
                TagEntity tag = h.getTagEntity();
                log.info(tag.getColumnName() + ":::" + tag.getId() + ":::" + h.getDtime() + ":::" + h.getValue());
                data(session, sDtime, enDtime, id);
            }*/
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

    private void data(Session session, Date sDtime, Date enDtime, long id) throws Exception {
        Criteria criteria = session.createCriteria(HourEntity.class)
                .add(Restrictions.between("dtime", sDtime, enDtime))
                .add(Restrictions.eq("id", id));
        List<HourEntity> result = criteria.list();
        for (HourEntity h : result) {
            log.info(h.getDtime() + ":::" + h.getValue());
        }
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