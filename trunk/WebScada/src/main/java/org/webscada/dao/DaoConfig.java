package org.webscada.dao;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.webscada.util.SessionUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DaoConfig<T, ID extends Serializable> extends AbstractDao<T, ID> {
    private final static Logger log = Logger.getLogger(DaoConfig.class);

    public DaoConfig(Class<T> persistanceClass) {
        super(persistanceClass);
    }

    @Override
    public List<T> getAllConfig() {
        Session session = SessionUtil.getSession();
        List<T> typeList = new ArrayList<>();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            Query rtuQuery = session.createQuery("from NodeEntity as node where node.type = 'rtu' ");
            List<T> rtuList = rtuQuery.list();
            for (T entity : rtuList) {
                typeList.add(entity);
            }

            Query tcpQuery = session.createQuery("from NodeEntity as node where node.type = 'tcp' ");
            List<T> tcpList = tcpQuery.list();
            for (T entity : tcpList) {
                typeList.add(entity);
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
        return typeList;
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
        }catch (Exception e) {
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
            e.printStackTrace();
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
