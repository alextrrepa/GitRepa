package dao;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.SessionUtil;

import java.io.Serializable;

public class AdminItemHibernateDao<T, ID extends Serializable> extends CommonOperationsHibernateDao<T, ID>
        implements AdminDaoIF<T, ID> {
    private final static Logger log = Logger.getLogger(AdminItemHibernateDao.class);

    public AdminItemHibernateDao(Class<T> persistenceClass) {
        super(persistenceClass);
    }

    @Override
    public T findRegByValue(Integer value) throws Exception {
        Session session = SessionUtil.getSession();
        Transaction transaction = null;
        T entity = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from RegisterEntity reg where reg.value=:val");
            query.setParameter("val", value);
            entity = (T) query.uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                try {
                    transaction.rollback();
                } catch (Exception ex) {
                    log.error("Rollback transaction error", ex);
                }
            }
            log.error("Original error when executing query", e);
        } finally {
            session.close();
        }
        return entity;
    }

    @Override
    public T findDataByValue(Integer value) throws Exception {
        Session session = SessionUtil.getSession();
        Transaction transaction = null;
        T entity = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from DatatypeEntity dat where dat.value=:val");
            query.setParameter("val", value);
            entity = (T) query.uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                try {
                    transaction.rollback();
                } catch (Exception ex) {
                    log.error("Rollback transaction error", ex);
                }
            }
            log.error("Original error when executing query", e);
        } finally {
            session.close();
        }
        return entity;
    }
}