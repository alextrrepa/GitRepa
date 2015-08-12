package modbusserver;

import com.serotonin.modbus4j.ModbusFactory;
import model.NodeEntity;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TransferQueue;

public abstract class ModbusType {
    protected ModbusFactory factory = new ModbusFactory();
    protected NodeEntity nodeEntity;

    public ModbusType(NodeEntity nodeEntity) {
        this.nodeEntity = nodeEntity;
    }

    public abstract void getTypes(List<ModbusTask> taskList, TransferQueue<Map<String, Map<String, Float>>> queue);
}
