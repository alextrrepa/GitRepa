package modbusserver;

import modbusserver.exception.ConfigException;
import org.apache.log4j.Logger;
import util.CheckConfigUtil;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class ModbusServer implements ServletContextListener {
    private final static Logger log = Logger.getLogger(ModbusServer.class);
    private List<ModbusTask> taskList = new ArrayList<>();
    private TransferQueue<Map<String, Map<String, Float>>> queue = new LinkedTransferQueue<>();
    private ThreadPoolExecutor executor;

    public ModbusServer() {
        CommonQueue.setQueue(queue);
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        log.trace("Starting Server....");
        try {
            CheckConfigUtil.check();
            new ModbusBridge(taskList, queue);
            executor = (ThreadPoolExecutor)Executors.newFixedThreadPool(taskList.size());
            for (ModbusTask task : taskList) {
                executor.execute(task);
            }
//            log.info("TaskList size :" + taskList.size());
/*
            executor = Executors.newScheduledThreadPool(taskList.size());
            for (ModbusTask task : taskList) {
                executor.scheduleAtFixedRate(task, 1, 2, TimeUnit.SECONDS);
            }
*/
            log.info("Server is started");
//            log.info("TaskList size :" + taskList.size());
        } catch (ConfigException e) {
            log.trace("Can't init configuration" + e);
            return;
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        executor.shutdown();
        log.info("Try to stop server.....");
        try {
            executor.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("Server is stoped ");
    }
}
