package org.webscada.modbusserver.config.dao;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.webscada.dao.AbstractDao;
import org.webscada.dao.DaoTreeParams;
import org.webscada.model.tree.DeviceParams;
import org.webscada.model.tree.NodeParams;

import java.util.List;

public class DaoTest {
    private final static Logger log = Logger.getLogger(DaoTest.class);

    @Test
    public void testAbstractDao() {
        AbstractDao treeDao = new DaoTreeParams();
        List<NodeParams> treeParams = treeDao.getTreeParams();
        for (NodeParams tree : treeParams) {
            log.trace(tree.getId());
            log.trace(tree.getTagType());
            log.trace(tree.getType());
            log.trace(tree.getName());

            List<DeviceParams> device = tree.getDeviceList();
            for (DeviceParams dev : device) {
                log.trace(dev.getId());
                log.trace(dev.getTagType());
                log.trace(dev.getName());
            }
        }
    }
}
