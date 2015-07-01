package org.webscada.dao;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.webscada.util.SessionUtil;

import java.util.List;

public class DaoTreeParams<T> extends AbstractDao<T> {
    private final static Logger log = Logger.getLogger(DaoTreeParams.class);

    @Override
    public List<T> getAll() {
        return null;
    }

    @Override
    public List<T> getTreeNodesParams() {
        Session session = SessionUtil.getSession();
        Transaction transaction = null;
        List<T> list = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("select node.id, node.name, node.type from NodeEntity as node where node.type = 'rtu' ");
            list = (List<T>) query.list();
            transaction.commit();
        }catch (Exception e) {
            try {
                transaction.rollback();
            } catch (Exception ex) {
                log.error("Rollback transaction error", ex);
            } finally {
                if (session != null) {
                    session.close();
                }
            }
        }finally{
            session.close();
        }
        return list;
    }
}
