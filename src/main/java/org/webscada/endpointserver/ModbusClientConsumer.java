package org.webscada.endpointserver;

import org.apache.log4j.Logger;
import org.webscada.modbusserver.CommonQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TransferQueue;

public class ModbusClientConsumer {
    private final Logger log = Logger.getLogger(this.getClass());
    private List<DataSourceListener> listeners = new ArrayList<>();
    private TransferQueue<Map<String, Map<String, Float>>> queue;
    private Thread thread;
    private boolean end;

    public ModbusClientConsumer() {
        this.queue = CommonQueue.getQueue();
        this.end = false;
    }

    public void start(){
        this.thread = new Thread(){
            @Override
            public void run() {
                while (!end){
                    try {
                        Map<String, Map<String, Float>> result = queue.take();
                        notifyListeners(result);
//                        log.info("Result in Client Consumer @@@" + result);
//                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        log.error("ModbusClientConsumer is Interrupted :", e.getCause());
                    }finally {
                        queue.clear();
                    }
                }
            }
        };
        this.thread.start();
    }

    public void stop(){
        this.end = true;
    }

    public void addDataSourceListener(DataSourceListener dsl){
        this.listeners.add(dsl);
    }

    private void notifyListeners(Map<String, Map<String, Float>> str){
        for (DataSourceListener l : listeners){
            l.handleNewData(str);
        }
    }
}
