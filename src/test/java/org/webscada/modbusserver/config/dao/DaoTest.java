package org.webscada.modbusserver.config.dao;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.webscada.dao.AbstractDao;
import org.webscada.dao.DaoConfig;
import org.webscada.model.NodeEntity;

public class DaoTest {
    private final static Logger log = Logger.getLogger(DaoTest.class);

    @Test
    public void testAbstractDao() {
        AbstractDao<NodeEntity, Long> testDao = new DaoConfig<>(NodeEntity.class);
        NodeEntity node = testDao.getById(new Long(2));
        log.trace(node.getName());
        log.trace(node.getType());
    }
}
