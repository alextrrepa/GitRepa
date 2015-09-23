package view.dao;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.SessionUtil;

import java.io.Serializable;
import java.util.List;

public abstract class GenericHibernateDAO<T, ID extends Serializable> implements GenericDao<T, ID> {
    private final static Logger log = Logger.getLogger(admin.dao.GenericHibernateDAO.class);
    private Class<T> persistanceClass;

    public GenericHibernateDAO(Class<T> persistanceClass) {
        this.persistanceClass = persistanceClass;
    }

    public Class<T> getPersistanceClass() {
        return persistanceClass;
    }

    @Override
    public List<T> getAllConfig() {
        Session session = SessionUtil.getSession();
        List<T> result = null;
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from NodeEntity as node");
            result = query.list();
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
        return result;
    }

    @Override
    public T getById(ID id) {
        Session session = SessionUtil.getSession();
        Transaction transaction = null;
        T entity = null;
        try {
            transaction = session.beginTransaction();
            entity = (T) session.get(getPersistanceClass(), id);
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
    public void create(T entity) {
        Session session = SessionUtil.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(entity);
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
    }

    @Override
    public void delete(ID id) {
        Session session = SessionUtil.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Object object = session.get(getPersistanceClass(), id);
            if (object != null) {
                session.delete(object);
            }
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
    }

    @Override
    public void update(T entity) {
        Session session = SessionUtil.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(entity);
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
    }
}
