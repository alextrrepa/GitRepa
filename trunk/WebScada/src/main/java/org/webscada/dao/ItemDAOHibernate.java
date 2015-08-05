package org.webscada.dao;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.webscada.util.SessionUtil;
import java.io.Serializable;

public class ItemDAOHibernate<T, ID extends Serializable> extends GenericHibernateDAO<T, ID> implements ItemDAO<T, ID> {
    private final static Logger log = Logger.getLogger(ItemDAOHibernate.class);

    public ItemDAOHibernate(Class<T> persistanceClass) {
        super(persistanceClass);
    }

    @Override
    public T findRegByValue(Integer value) {
        Session session = SessionUtil.getSession();
        Transaction transaction = null;
        T entity = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from RegisterEntity reg where reg.value=:val");
            query.setParameter("val", value);
            entity  = (T) query.uniqueResult();
            transaction.commit();
        }catch (Exception e) {
            e.printStackTrace();
            try {
                transaction.rollback();
            }catch (Exception ex) {
                log.error("Rollback transaction error", ex);
            }
        }finally {
            session.close();
        }
        return entity;
    }

    @Override
    public T findDataByValue(Integer value) {
        Session session = SessionUtil.getSession();
        Transaction transaction = null;
        T entity = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from DatatypeEntity dat where dat.value=:val");
            query.setParameter("val", value);
            entity = (T) query.uniqueResult();
            transaction.commit();
        }catch (Exception e) {
            e.printStackTrace();
            try {
                transaction.rollback();
            }catch (Exception ex) {
                log.error("Rollback transaction error", ex);
            }
        }finally {
            session.close();
        }
        return entity;
    }
}
