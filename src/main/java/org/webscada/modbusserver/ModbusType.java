package org.webscada.modbusserver;

import com.serotonin.modbus4j.ModbusFactory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TransferQueue;

public abstract class ModbusType {
    protected ModbusFactory factory = new ModbusFactory();
    protected Node node;

    public ModbusType(Node node) {
        this.node = node;
    }

    public abstract void getTypes(List<ModbusTask> taskList, TransferQueue<Map<String, Map<String, Float>>> queue);
}
