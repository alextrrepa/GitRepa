package dao;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.SessionUtil;

import java.io.Serializable;
import java.util.List;

public abstract class CommonOperationsHibernateDao<T, ID extends Serializable> implements
        CommonOperationsDaoIF<T, ID> {
    private final static Logger log = Logger.getLogger(CommonOperationsHibernateDao.class);
    private Class<T> persistenceClass;

    public CommonOperationsHibernateDao(Class<T> persistenceClass) {
        this.persistenceClass = persistenceClass;
    }

    protected Class<T> getPersistenceClass() {
        return persistenceClass;
    }

    @Override
    public List<T> getAll() {
        Session session = SessionUtil.getSession();
        List<T> result = null;
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
//            Query query = session.createQuery("from NodeEntity as node");
            Criteria criteria = session.createCriteria(getPersistenceClass());
            result = criteria.list();
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
        return result;
    }

    @Override
    public T getById(ID id) {
        Session session = SessionUtil.getSession();
        Transaction transaction = null;
        T entity = null;
        try {
            transaction = session.beginTransaction();
            entity = (T) session.get(getPersistenceClass(), id);
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
    public void create(T entity) {
        Session session = SessionUtil.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(entity);
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
    }

    @Override
    public void delete(ID id) {
        Session session = SessionUtil.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Object object = session.get(getPersistenceClass(), id);
            if (object != null) {
                session.delete(object);
            }
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
    }

    @Override
    public void update(T entity) {
        Session session = SessionUtil.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(entity);
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
    }
}