package org.webscada.modbusserver;

import com.serotonin.io.serial.SerialParameters;
import com.serotonin.modbus4j.ModbusMaster;
import org.apache.log4j.Logger;
import org.webscada.dao.AbstractDao;
import org.webscada.model.DeviceEntity;
import org.webscada.model.NodeEntity;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TransferQueue;

public class ModbusRtu extends ModbusType {
    private final static Logger log = Logger.getLogger(ModbusRtu.class);

    public ModbusRtu(AbstractDao<NodeEntity> dao) {
        super(dao);
    }

    @Override
    public void getTypes(List<ModbusTask> taskList, TransferQueue<Map<String, Map<String, Float>>> queue) {
        List<NodeEntity> rtuNodes = dao.getAll();
        for (NodeEntity node : rtuNodes) {
            SerialParameters serialParameters = new SerialParameters();
            String name = node.getRtuEntity().getPort();

            serialParameters.setCommPortId(node.getRtuEntity().getPort());
            serialParameters.setBaudRate(node.getRtuEntity().getBaudrate());
            serialParameters.setDataBits(node.getRtuEntity().getDatabits());
            serialParameters.setStopBits(node.getRtuEntity().getStopbits());
            serialParameters.setParity(node.getRtuEntity().getParity());

            ModbusMaster master = factory.createRtuMaster(serialParameters);
            master.setTimeout(node.getRtuEntity().getTimeout());
            master.setRetries(node.getRtuEntity().getRetries());
            log.trace(node.getName());
            log.trace(node.getType());

            int period = node.getRtuEntity().getPeriod();
            List<DeviceEntity> devList = node.getDeviceEntity();
            for (DeviceEntity device : devList) {
                log.trace(device.getName());
            }
            taskList.add(new ModbusTask(master, devList, queue, name, period));
        }
    }
}
