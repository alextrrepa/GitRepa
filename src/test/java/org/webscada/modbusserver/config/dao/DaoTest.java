package org.webscada.modbusserver.config.dao;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.webscada.dao.AbstractDao;
import org.webscada.dao.DaoConfig;
import org.webscada.model.NodeEntity;
import org.webscada.model.RtuEntity;
import org.webscada.model.TcpEntity;

import java.util.List;

public class DaoTest {
    private final static Logger log = Logger.getLogger(DaoTest.class);

    @Test
    public void testAbstractDao() {
        AbstractDao dao = new DaoConfig(NodeEntity.class);
        List<NodeEntity> entityList = dao.getAllConfig();
        for (NodeEntity entity : entityList) {
            log.trace(entity.getName());
            String type = entity.getType();
            if (type.equalsIgnoreCase("rtu")) {
                RtuEntity rtuEntity = entity.getRtuEntity();
                log.trace(rtuEntity.getPort());
            }
            if (type.equalsIgnoreCase("tcp")) {
                TcpEntity tcpEntity = entity.getTcpEntity();
                log.trace(tcpEntity.getIp());
            }
        }
    }
}
