package org.webscada.modbusserver.config.dao;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.webscada.controllers.tree_edit_delegation.Operation;
import org.webscada.dao.*;
import org.webscada.model.NodeEntity;
import org.webscada.model.RegisterEntity;
import org.webscada.model.RtuEntity;
import org.webscada.model.TcpEntity;

import java.util.List;

public class DaoTest {
    private final static Logger log = Logger.getLogger(DaoTest.class);

    @Test
    public void testAbstractDao() {
        GenericDao config = new ItemDAOHibernate(NodeEntity.class);
        try {
            List<NodeEntity> listConfig = config.getAllConfig();
            for (NodeEntity node : listConfig) {
                if (node.getType().equalsIgnoreCase("rtu")) {
                    log.trace(node);
//                    RtuEntity rtuEntity = node.getRtuEntity();
//                    log.trace(node.getName());
//                    log.trace(rtuEntity.getPort());
                }

                if (node.getType().equalsIgnoreCase("tcp")) {
                    log.trace(node);
//                    TcpEntity tcpEntity = node.getTcpEntity();
//                    log.trace(node.getName());
//                    log.trace(tcpEntity.getIp());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
