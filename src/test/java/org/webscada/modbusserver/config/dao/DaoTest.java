package org.webscada.modbusserver.config.dao;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.webscada.dao.AbstractDao;
import org.webscada.dao.DaoConfig;
import org.webscada.dao.DaoTreeParams;
import org.webscada.model.*;

import java.util.List;

public class DaoTest {
    private final static Logger log = Logger.getLogger(DaoTest.class);

    @Test
    public void testAbstractDao() {
        AbstractDao dao = new DaoTreeParams();
        List<Object[]> list = dao.getTreeNodesParams();
        for (Object[] obj : list) {
            Long id = (Long) obj[0];
            log.trace(id);
            String name = (String) obj[1];
            log.trace(name);
            String type = (String) obj[2];
            log.trace(type);
        }

 /*       dao = new DaoConfig();
        List<NodeEntity> entityList = dao.getAll();
        for (NodeEntity entity : entityList) {
            log.trace(entity.getName());
        }*/
    }
}
