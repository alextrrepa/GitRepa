package org.webscada.modbusserver.config.dao;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.webscada.util.SessionUtil;
import org.webscada.modbusserver.NodeRtu;
import org.webscada.entities.NodeEntity;

import java.util.List;

public class DaoTest {
    private final static Logger log = Logger.getLogger(DaoTest.class);

    @Test
    public void testModbusDao() {
        Session session = SessionUtil.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
//            Query query = session.createQuery("from NodeEntity as node where node.type = 'rtu'");
//            query.list();

            Criteria criteria = session.createCriteria(NodeEntity.class);
            criteria.add(Restrictions.eq ("type", "rtu"))
                    .add(Restrictions.isNotNull("type"))
                    .add(Restrictions.isNotNull("rtuEntity"))
                    .add(Restrictions.isNotNull("tcpEntity"))
                    .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            Restrictions.isEmpty("");
            List<NodeEntity> resultList = criteria.list();

            transaction.commit();
        }catch (Exception ex) {
            log.error("Transaction Error", ex.fillInStackTrace());
            try {
                transaction.rollback();
            }catch (Exception e) {
                log.error("Can't rollback transaction", e.fillInStackTrace());
            } finally {
                if (session != null) {
                    session.close();
                }
            }
        }finally {
            session.close();
        }
    }

    @Test
    public void testNodeRtu() {
        NodeRtu rtu = new NodeRtu();
        rtu.getNodes();
    }
}
