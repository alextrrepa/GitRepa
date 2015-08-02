package org.webscada.modbusserver.config.dao;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.webscada.dao.AbstractDao;
import org.webscada.dao.DaoConfig;
import org.webscada.model.NodeEntity;
import org.webscada.model.RtuEntity;

public class DaoTest {
    private final static Logger log = Logger.getLogger(DaoTest.class);

    @Test
    public void testAbstractDao() {
        AbstractDao<NodeEntity, Long> dao = new DaoConfig<>(NodeEntity.class);
        NodeEntity entity = dao.getById(new Long(2));
        entity.setName("Node2");
        RtuEntity rtuEntity = entity.getRtuEntity();
        rtuEntity.setPort("COM666");
        dao.update(entity);
    }
}
