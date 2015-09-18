package ru.scada.dao;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import ru.scada.model.TagEntity;
import ru.scada.util.SessionUtil;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ItemDao<T> implements GenericDao<T> {
    private final Logger log = Logger.getLogger(ItemDao.class);
    private Class<T> persistanceClass;

    public ItemDao(Class<T> persistanceClass) {
        this.persistanceClass = persistanceClass;
    }

    public Class<T> getPersistanceClass() {
        return persistanceClass;
    }

    @Override
    public Map<String, List<T>> showData(Date sDtime, Date enDtime) {
        Map<String, List<T>> listMap = new LinkedHashMap<>();
        Session session = SessionUtil.getSession();
//        List<T> result = null;
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(TagEntity.class);
//                    .add(Restrictions.between("dtime", sDtime, enDtime))
//                    .addOrder(Order.asc("tagEntity.id"));
            List<TagEntity> result = criteria.list();
            for (TagEntity tag : result) {
//                System.out.println(tag.getColumnName() + ":::" + tag.getId());
//                log.info(tag.getColumnName() + ":::" + tag.getId());
                listMap.put(tag.getColumnName(), data(session, sDtime, enDtime));
            }
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
        return listMap;
    }

    private List<T> data(Session session, Date sDtime, Date enDtime) throws Exception {
        Criteria criteria = session.createCriteria(getPersistanceClass())
                .add(Restrictions.between("dtime", sDtime, enDtime))
                .addOrder(Order.asc("dtime"));
        List<T> result = criteria.list();
        return result;
/*
        for (HourEntity h : result) {
            System.out.println(h.getDtime() + ":::" + h.getValue());
            log.info(h.getDtime() + ":::" + h.getValue());
        }
*/
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