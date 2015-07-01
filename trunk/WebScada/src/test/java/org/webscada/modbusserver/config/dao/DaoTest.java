package org.webscada.modbusserver.config.dao;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.webscada.dao.AbstractDao;
import org.webscada.dao.DaoConfig;
import org.webscada.model.*;

import java.util.List;

public class DaoTest {
    private final static Logger log = Logger.getLogger(DaoTest.class);

    @Test
    public void testAbstractDao() {
        AbstractDao dao = null;
        dao = new DaoConfig();
        List<NodeEntity> list = dao.getAll();
        for (NodeEntity entity : list) {
            log.trace(entity.getName());
            String nodeType = entity.getType();
            if (nodeType.equalsIgnoreCase("rtu")) {
                RtuEntity rtuEntity = entity.getRtuEntity();
                log.trace(rtuEntity);
            }
            if (nodeType.equalsIgnoreCase("tcp")) {
                TcpEntity tcpEntity = entity.getTcpEntity();
                log.trace(tcpEntity.getIp());
            }
            /*List<DeviceEntity> deviceList = entity.getDeviceEntity();
            for (DeviceEntity dev : deviceList) {
                log.trace(dev.getName());
                List<TagEntity> tagList = dev.getTagEntities();
                for (TagEntity tag : tagList) {
                    log.trace(tag.getName());
                }
            }*/
        }
    }
}
