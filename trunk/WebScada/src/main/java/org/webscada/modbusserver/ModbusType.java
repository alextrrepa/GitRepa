package org.webscada.modbusserver;

import com.serotonin.modbus4j.ModbusFactory;
import org.webscada.dao.AbstractDao;
import org.webscada.entities.NodeEntity;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TransferQueue;

public abstract class ModbusType {
    protected ModbusFactory factory = new ModbusFactory();
    protected AbstractDao<NodeEntity> dao;

    public ModbusType(AbstractDao<NodeEntity> dao) {
        this.dao = dao;
    }

    public abstract void getTypes(List<ModbusTask> taskList, TransferQueue<Map<String, Map<String, Float>>> queue);
}
