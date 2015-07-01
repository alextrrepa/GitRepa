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

public class ConfigDaoRtu extends AbstractDao {
    private final static Logger log = Logger.getLogger(ConfigDaoRtu.class);

    @Override
    public List<NodeEntity> getAll() {
        List<NodeEntity> rtuEntities = new ArrayList<>();
        Session session = SessionUtil.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from NodeEntity as node where node.type = 'rtu' ");
            rtuEntities = query.list();
            transaction.commit();
        }catch (Exception e) {
            try {
                transaction.rollback();
            }catch (Exception ex) {
                log.error("Rollback transaction error", ex);
            }finally {
                if (session !=null) {
                    session.close();
                }
            }
        }finally {
            session.close();
        }
        return rtuEntities;
    }

    @Override
    public List<NodeParams> getTreeParams() {
        throw new UnsupportedOperationException();
    }
}
