package view.modbusserver;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ModbusServer implements ServletContextListener {
    /*private final static Logger log = Logger.getLogger(ModbusServer.class);
    private List<ModbusTask> taskList = new ArrayList<>();
    private TransferQueue<Map<String, Map<String, Float>>> queue = new LinkedTransferQueue<>();
    private ThreadPoolExecutor executor;*/

//    public ModbusServer() {
//        CommonQueue.setQueue(queue);
//    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
/*        log.info("Starting Server....");
        try {
            CheckConfigUtil.check();
            new ModbusBridge(taskList, queue);
            executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(taskList.size());
            for (ModbusTask task : taskList) {
                executor.execute(task);
            }
        } catch (ConfigException e) {
            e.printStackTrace();
        }
        log.info("Server is started");*/
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
/*        executor.shutdown();
        log.info("Try to stop server.....");
        try {
            executor.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            log.error("Interrupt error" + e);
        }
        log.info("Server is stoped ");*/
    }
}