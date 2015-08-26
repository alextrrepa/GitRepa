package ru.scada.modbusserver;

import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.ip.IpParameters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.scada.model.DeviceEntity;
import ru.scada.model.NodeEntity;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TransferQueue;

public class ModbusTcp extends ModbusType {
    private final static Logger log = LogManager.getLogger(ModbusTcp.class);

    public ModbusTcp(NodeEntity nodeEntity) {
        super(nodeEntity);
    }

    @Override
    public void getTypes(List<ModbusTask> taskList, TransferQueue<Map<String, Map<String, Float>>> queue) {
        String portName = nodeEntity.getName();
        IpParameters ipParameters = new IpParameters();
        ipParameters.setHost(nodeEntity.getTcpEntity().getIp());
        ipParameters.setPort(nodeEntity.getTcpEntity().getPort());

        ModbusMaster master = factory.createTcpMaster(ipParameters, true);
        master.setRetries(nodeEntity.getTcpEntity().getRetries());
        master.setTimeout(nodeEntity.getTcpEntity().getTimeout());

        int period = nodeEntity.getTcpEntity().getPeriod();
        List<DeviceEntity> deviceList = nodeEntity.getDeviceEntity();
        taskList.add(new ModbusTask(master, deviceList, queue, portName , period));
    }
}
