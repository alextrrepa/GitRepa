package org.webscada.modbusserver;

import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.ip.IpParameters;
import org.apache.log4j.Logger;
import org.webscada.model.DeviceEntity;
import org.webscada.model.NodeEntity;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TransferQueue;

public class ModbusTcp extends ModbusType {
    private final static Logger log = Logger.getLogger(ModbusTcp.class);

    public ModbusTcp(NodeEntity nodeEntity) {
        super(nodeEntity);
    }

    @Override
    public void getTypes(List<ModbusTask> taskList, TransferQueue<Map<String, Map<String, Float>>> queue) {
        String name = nodeEntity.getName();
        log.info(name);
        log.info(nodeEntity.getType());
        IpParameters ipParameters = new IpParameters();
        log.info(nodeEntity.getTcpEntity().getIp());
        ipParameters.setHost(nodeEntity.getTcpEntity().getIp());
        log.info(nodeEntity.getTcpEntity().getPort());
        ipParameters.setPort(nodeEntity.getTcpEntity().getPort());
        ModbusMaster master = factory.createTcpMaster(ipParameters, true);
        log.info(nodeEntity.getTcpEntity().getRetries());
        master.setRetries(nodeEntity.getTcpEntity().getRetries());
        log.info(nodeEntity.getTcpEntity().getTimeout());
        master.setTimeout(nodeEntity.getTcpEntity().getTimeout());

        int period = nodeEntity.getTcpEntity().getPeriod();

        List<DeviceEntity> deviceList = nodeEntity.getDeviceEntity();
        for (DeviceEntity device : deviceList) {
            log.trace(device.getName());
        }
        taskList.add(new ModbusTask(master, deviceList, queue, name, period));
    }
}
