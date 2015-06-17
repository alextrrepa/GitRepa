package org.webscada.modbusserver.config.dao;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.webscada.dao.AbstractDao;
import org.webscada.dao.ConfigDaoFactory;
import org.webscada.util.SessionUtil;
import org.webscada.modbusserver.NodeRtu;
import org.webscada.entities.NodeEntity;

import java.util.List;

public class DaoTest {
    private final static Logger log = Logger.getLogger(DaoTest.class);

    @Test
    public void testAbstractDao() {
        ConfigDaoFactory factory = new ConfigDaoFactory();
        AbstractDao<NodeEntity> rtu = factory.getDao("rtu");
        List<NodeEntity> rtuList = rtu.getAll();
        for (NodeEntity entity : rtuList) {
            log.trace(entity.getName());
        }

        AbstractDao<NodeEntity> tcp = factory.getDao("tcp");
        List<NodeEntity> tcpList = tcp.getAll();
        for (NodeEntity entity : tcpList) {
            log.trace(entity.getName());
        }
    }
}
