package org.webscada.dao;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.webscada.util.SessionUtil;

import java.util.ArrayList;
import java.util.List;

public class DaoConfig<T> extends AbstractDao<T> {
    private final static Logger log = Logger.getLogger(DaoConfig.class);

    @Override
    public List<T> getAll() {
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
            } finally {
                if (session != null) {
                    session.close();
                }
            }
        } finally {
            session.close();
        }
        return typeList;
    }

    @Override
    public List<T> getTreeNodesParams() {
        return null;
    }
}
