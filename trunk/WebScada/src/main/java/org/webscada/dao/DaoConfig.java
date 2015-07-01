package org.webscada.dao;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.webscada.model.NodeEntity;
import org.webscada.model.tree.NodeParams;
import org.webscada.util.SessionUtil;

import java.util.ArrayList;
import java.util.List;

public class DaoConfig extends AbstractDao {
    private final static Logger log = Logger.getLogger(DaoConfig.class);

    @Override
    public List<NodeEntity> getAll() {
        Session session = SessionUtil.getSession();
        List<NodeEntity> typeList = new ArrayList<>();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            Query rtuQuery = session.createQuery("from NodeEntity as node where node.type = 'rtu' ");
            List<NodeEntity> rtuList = rtuQuery.list();
            for (NodeEntity entity : rtuList) {
                typeList.add(entity);
            }

            Query tcpQuery = session.createQuery("from NodeEntity as node where node.type = 'tcp' ");
            List<NodeEntity> tcpList = tcpQuery.list();
            for (NodeEntity entity : tcpList) {
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
    public List<NodeParams> getTreeParams() {
        throw new UnsupportedOperationException();
    }
}
