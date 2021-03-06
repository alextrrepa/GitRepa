package view.modbusserver;

import com.serotonin.io.serial.SerialParameters;
import com.serotonin.modbus4j.ModbusMaster;
import entities.DeviceEntity;
import entities.NodeEntity;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TransferQueue;

public class ModbusRtu extends ModbusType {
    private final static Logger log = Logger.getLogger(ModbusRtu.class);

    public ModbusRtu(NodeEntity nodeEntityEntity) {
        super(nodeEntityEntity);
    }

    @Override
    public void getTypes(List<ModbusTask> taskList, TransferQueue<Map<String, Map<String, Float>>> queue) {
        SerialParameters serialParameters = new SerialParameters();
        String portName = nodeEntity.getRtuEntity().getPort();
        serialParameters.setCommPortId(nodeEntity.getRtuEntity().getPort());
        serialParameters.setBaudRate(nodeEntity.getRtuEntity().getBaudrate());
        serialParameters.setDataBits(nodeEntity.getRtuEntity().getDatabits());
        serialParameters.setStopBits(nodeEntity.getRtuEntity().getStopbits());
        serialParameters.setParity(nodeEntity.getRtuEntity().getParity());
        ModbusMaster master = factory.createRtuMaster(serialParameters);
        master.setTimeout(nodeEntity.getRtuEntity().getTimeout());
        master.setRetries(nodeEntity.getRtuEntity().getRetries());
        int period = nodeEntity.getRtuEntity().getPeriod();
        List<DeviceEntity> devList = nodeEntity.getDeviceEntity();
        taskList.add(new ModbusTask(master, devList, queue, portName, period));
    }
}
