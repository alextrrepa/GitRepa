package org.webscada.modbusserver.config.dao;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.webscada.dao.AbstractDao;
import org.webscada.dao.DaoConfig;
import org.webscada.model.NodeEntity;

import java.util.List;

public class DaoTest {
    private final static Logger log = Logger.getLogger(DaoTest.class);

    @Test
    public void testAbstractDao() {
        AbstractDao dao = new DaoConfig(NodeEntity.class);
        List<NodeEntity> entityList = dao.getAllConfig();
        for (NodeEntity entity : entityList) {
            log.trace(entity.getName());
        }
    }
}
