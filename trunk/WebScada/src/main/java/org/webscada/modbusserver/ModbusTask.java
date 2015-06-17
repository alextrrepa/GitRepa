package org.webscada.modbusserver;

import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.exception.ModbusInitException;
import com.serotonin.modbus4j.exception.ModbusTransportException;
import com.serotonin.modbus4j.msg.ReadHoldingRegistersRequest;
import com.serotonin.modbus4j.msg.ReadHoldingRegistersResponse;
import org.apache.log4j.Logger;
import org.webscada.entities.DeviceEntity;
import org.webscada.entities.TagEntity;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TransferQueue;

public class ModbusTask implements Runnable {
    private final static Logger log = Logger.getLogger(ModbusTask.class);
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
            log.trace("Port " + portName + " is open : " + master.isInitialized());
            while (!Thread.currentThread().isInterrupted()) {
                Map<String, Map<String, Float>> values = startPolling();
                for (Map.Entry<String, Map<String, Float>> val : values.entrySet()) {
                    log.trace(val.getKey() + " : " + val.getValue());
                }
                if (queue.hasWaitingConsumer()) {
                    queue.transfer(values);
                }
                TimeUnit.SECONDS.sleep(period);
            }
        } catch (ModbusInitException e) {
            log.error("Can't init port " + portName);
        } catch (ModbusTransportException e) {
            log.error("Error in request/responce operation", e.getCause());
        } catch (InterruptedException e) {
            log.error("Current thread interrupted" + Thread.currentThread().getName(), e.getCause());
        }finally {
            master.destroy();
            log.trace("Destroy Master");
        }
    }

    private Map<String, Map<String, Float>> startPolling() throws ModbusTransportException {
        Map<String, Map<String, Float>> packValues = new LinkedHashMap<>();
        for (int i = 0; i < deviceList.size(); i++) {
            DeviceEntity device = deviceList.get(i);
            ReadHoldingRegistersRequest request = new ReadHoldingRegistersRequest(device.getSlaveid(),
                    device.getStartOffset(), device.getCounts());
            ReadHoldingRegistersResponse response = (ReadHoldingRegistersResponse)master.send(request);
            if (response.isException()) {
                log.error("Error responce in " + device.getName());
            }
            byte [] data = response.getData();
            ModbusLocator locator = new ModbusLocator(device.getRegisterEntity().getValue(), data);
            String devName = device.getName();
            log.error(devName);
            Map<String, Float> packTagValues = new LinkedHashMap<>();
            List<TagEntity> tagList = device.getTagEntities();
            for (int  j = 0; j < tagList.size(); j++) {
                TagEntity tag = tagList.get(j);
                float value = (float) locator.getValue(tag.getRealOffset(), tag.getDatatypeEntity().getValue());
                log.trace(tag.getName());
                packTagValues.put(tag.getName(), value);
            }
            packValues.put(devName, packTagValues);
        }
        return packValues;
    }
}
