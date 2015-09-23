package view.modbusserver;

import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.exception.ModbusInitException;
import com.serotonin.modbus4j.exception.ModbusTransportException;
import com.serotonin.modbus4j.msg.ReadHoldingRegistersRequest;
import com.serotonin.modbus4j.msg.ReadHoldingRegistersResponse;
import entities.CurrentEntity;
import entities.DeviceEntity;
import entities.TagEntity;
import org.apache.log4j.Logger;
import view.dao.GenericDao;
import view.dao.ItemDAOHibernate;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TransferQueue;

public class ModbusTask implements Runnable {
    private static final Logger log = Logger.getLogger(ModbusTask.class);
    private ModbusMaster master;
    private List<DeviceEntity> deviceList;
    private TransferQueue<Map<String, Map<String, Float>>> queue;
    private String portName;
    private int period;

    public ModbusTask(ModbusMaster master, List<DeviceEntity> deviceList,
                      TransferQueue<Map<String, Map<String, Float>>> queue, String portName, int period) {
        this.master = master;
        this.deviceList = deviceList;
        this.queue = queue;
        this.portName = portName;
        this.period = period;
    }

    @Override
    public void run() {
        try {
            master.init();
            log.info("Port " + portName + " is open : " + master.isInitialized());
            while (true) {
                Map<String, Map<String, Float>> values = startPolling();
//                for (Map.Entry<String, Map<String, Float>> val : values.entrySet()) {
//                    System.out.println(val.getKey() + " : " + val.getValue());
//                }
                if (queue.hasWaitingConsumer()) {
                    queue.transfer(values);
                }
                TimeUnit.SECONDS.sleep(period);
            }
        } catch (ModbusInitException e) {
            log.error("Can't init port " + portName);
//            Thread.currentThread().interrupt();
        } catch (InterruptedException e) {
            log.error("Current thread interrupted" + Thread.currentThread().getName());
//            Thread.currentThread().interrupt();
        } finally {
            log.info("Destroy Master");
            master.destroy();
        }
    }

    private Map<String, Map<String, Float>> startPolling() {
        Map<String, Map<String, Float>> packValues = new LinkedHashMap<>();
        for (int i = 0; i < deviceList.size(); i++) {
            DeviceEntity device = deviceList.get(i);
            try {
                ReadHoldingRegistersRequest request = new ReadHoldingRegistersRequest(device.getSlaveid(),
                        device.getStartOffset(), device.getCounts());
                ReadHoldingRegistersResponse response = (ReadHoldingRegistersResponse) master.send(request);
                byte[] data = response.getData();
                ModbusLocator locator = new ModbusLocator(device.getRegisterEntity().getValue(), data);
                String devName = device.getName();
                Map<String, Float> packTagValues = new LinkedHashMap<>();
                List<TagEntity> tagList = device.getTagEntities();
                for (int j = 0; j < tagList.size(); j++) {
                    TagEntity tag = tagList.get(j);
                    float value = (float) locator.getValue(tag.getRealOffset(), tag.getDatatypeEntity().getValue());
                    insertCurrentData(tag.getId(), value);
                    packTagValues.put(tag.getName(), value);
                }
                packValues.put(devName, packTagValues);
            } catch (ModbusTransportException e) {
                log.error("Error responce in " + device.getName());
                deviceList.remove(i);
            }
        }
        return packValues;
    }

    private void insertCurrentData(long id, float value) {
        GenericDao<CurrentEntity, Long> curData = new ItemDAOHibernate<>(CurrentEntity.class);
        CurrentEntity curEntity = new CurrentEntity();
        curEntity.setDatetime(new Date());
        curEntity.setTag_id(id);
        curEntity.setValue(value);
        curData.create(curEntity);
    }
}
