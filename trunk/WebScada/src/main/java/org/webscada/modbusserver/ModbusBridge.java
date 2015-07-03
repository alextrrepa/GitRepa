package org.webscada.modbusserver;

import org.apache.log4j.Logger;
import org.webscada.dao.AbstractDao;
import org.webscada.dao.DaoConfig;
import org.webscada.model.NodeEntity;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TransferQueue;

public class ModbusBridge {
    private final static Logger log = Logger.getLogger(ModbusBridge.class);

    public ModbusBridge(List<ModbusTask> taskList,
                        TransferQueue<Map<String, Map<String, Float>>> queue) {
        getTypeConfig(taskList, queue);
    }

    private void getTypeConfig(List<ModbusTask> taskList,
                               TransferQueue<Map<String, Map<String, Float>>> queue) {
        ModbusType modbusType;
        AbstractDao config = new DaoConfig(NodeEntity.class);
        List<NodeEntity> listConfig = config.getAllConfig();
        for (NodeEntity entity : listConfig) {
            if (entity.getType().equalsIgnoreCase("rtu")) {
                modbusType = new ModbusRtu(entity);
                modbusType.getTypes(taskList, queue);
            }

            if (entity.getType().equalsIgnoreCase("tcp")) {
                modbusType = new ModbusTcp(entity);
                modbusType.getTypes(taskList, queue);
            }
        }
    }
}
