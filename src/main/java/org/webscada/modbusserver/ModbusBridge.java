package org.webscada.modbusserver;

import org.apache.log4j.Logger;
import org.webscada.dao.AbstractDao;
import org.webscada.dao.ConfigDaoFactory;
import org.webscada.entities.NodeEntity;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TransferQueue;

public class ModbusBridge {
    private final static Logger log = Logger.getLogger(ModbusBridge.class);
    private ConfigDaoFactory factory = new ConfigDaoFactory();

    public ModbusBridge(Set<String> typeSet, List<ModbusTask> taskList,
                        TransferQueue<Map<String, Map<String, Float>>> queue) {
        getTypeConfig(typeSet,taskList, queue);
    }

    private void getTypeConfig(Set<String> typeSet, List<ModbusTask> taskList,
                               TransferQueue<Map<String, Map<String, Float>>> queue) {

        for (String type : typeSet) {
            AbstractDao<NodeEntity> entity = factory.getDao(type);
            if (type.equalsIgnoreCase("rtu")) {
                ModbusType rtuType = new ModbusRtu(entity);
                rtuType.getTypes(taskList, queue);
            }
            if (type.equalsIgnoreCase("tcp")) {
                ModbusType tcpType = new ModbusTcp(entity);
                tcpType.getTypes(taskList, queue);
            }
        }
    }
}
