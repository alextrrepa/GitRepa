package ru.scada.modbusserver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.scada.dao.GenericDao;
import ru.scada.dao.ItemDAOHibernate;
import ru.scada.model.NodeEntity;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TransferQueue;

public class ModbusBridge {
    private final static Logger log = LogManager.getLogger(ModbusBridge.class);

    public ModbusBridge(List<ModbusTask> taskList,
                        TransferQueue<Map<String, Map<String, Float>>> queue) {
        getTypeConfig(taskList, queue);
    }

    private void getTypeConfig(List<ModbusTask> taskList,
                               TransferQueue<Map<String, Map<String, Float>>> queue) {
        GenericDao config = new ItemDAOHibernate(NodeEntity.class);
        List<NodeEntity> listConfig = config.getAllConfig();
        ModbusType modbusType;
        for (NodeEntity entity : listConfig) {
            try {
                if (entity.getType().equalsIgnoreCase("rtu")) {
                    modbusType = new ModbusRtu(entity);
                    modbusType.getTypes(taskList, queue);
                }
                if (entity.getType().equalsIgnoreCase("tcp")) {
                    modbusType = new ModbusTcp(entity);
                    modbusType.getTypes(taskList, queue);
                }
            }catch (NullPointerException ex) {
                log.error("Can't get configuration for " + entity.getName());
            }
        }
    }
}
