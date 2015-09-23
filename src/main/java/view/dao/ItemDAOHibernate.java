package view.dao;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.SessionUtil;

import java.io.Serializable;
import java.util.List;

public class ItemDAOHibernate<T, ID extends Serializable> extends GenericHibernateDAO<T, ID> implements ItemDAO<T, ID> {
    private final static Logger log = Logger.getLogger(admin.dao.ItemDAOHibernate.class);

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
            entity = (T) query.uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            log.error(e);
            try {
                transaction.rollback();
            } catch (Exception ex) {
                log.error("Rollback transaction error", ex);
            }
        } finally {
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
        } catch (Exception e) {
            log.error(e);
            try {
                transaction.rollback();
            } catch (Exception ex) {
                log.error("Rollback transaction error", ex);
            }
        } finally {
            session.close();
        }
        return entity;
    }

    @Override
    public List<T> getTags() {
        Session session = SessionUtil.getSession();
        Transaction transaction = null;
        List<T> tags = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from TagEntity");
            tags = query.list();
            transaction.commit();
        } catch (Exception e) {
            log.error(e);
            try {
                transaction.rollback();
            } catch (Exception ex) {
                log.error("Rollback transaction error", ex);
            }
        }
        return tags;
    }
}
