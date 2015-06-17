package org.webscada.modbusserver;

import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.ip.IpParameters;
import org.apache.log4j.Logger;
import org.webscada.dao.AbstractDao;
import org.webscada.entities.NodeEntity;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TransferQueue;

public class ModbusTcp extends ModbusType {
    private final static Logger log = Logger.getLogger(ModbusTcp.class);

    public ModbusTcp(AbstractDao<NodeEntity> dao) {
        super(dao);
    }

    @Override
    public void getTypes(List<ModbusTask> taskList, TransferQueue<Map<String, Map<String, Float>>> queue) {
        List<NodeEntity> tcpNodes = dao.getAll();
        for (NodeEntity entity : tcpNodes) {
            String name = entity.getName();
            log.info(name);
            log.info(entity.getType());
            IpParameters ipParameters = new IpParameters();
            log.info(entity.getTcpEntity().getIp());
            ipParameters.setHost(entity.getTcpEntity().getIp());
            log.info(entity.getTcpEntity().getPort());
            ipParameters.setPort(entity.getTcpEntity().getPort());
            ModbusMaster master = factory.createTcpMaster(ipParameters, true);
            log.info(entity.getTcpEntity().getRetries());
            master.setRetries(entity.getTcpEntity().getRetries());
            log.info(entity.getTcpEntity().getTimeout());
            master.setTimeout(entity.getTcpEntity().getTimeout());

            int period = entity.getTcpEntity().getPeriod();

            /*List<DeviceEntity> deviceList = entity.getDeviceEntity();
            taskList.add(new ModbusTask(master, deviceList, queue, name, period));*/
        }
    }
}
