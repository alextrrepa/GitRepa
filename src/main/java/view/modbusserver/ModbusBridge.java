package view.modbusserver;

import dao.CommonOperationsHibernateDao;
import dao.ViewItemHibernateDao;
import entities.NodeEntity;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TransferQueue;

public class ModbusBridge {
    private final static Logger log = Logger.getLogger(ModbusBridge.class);

    public ModbusBridge(List<ModbusTask> taskList,
                        TransferQueue<Map<String, Map<String, Float>>> queue) {
        getTypeConfig(taskList, queue);
    }

    private void getTypeConfig(List<ModbusTask> taskList,
                               TransferQueue<Map<String, Map<String, Float>>> queue) {
//        GenericDao config = new ItemDAOHibernate(NodeEntity.class);
        CommonOperationsHibernateDao<NodeEntity, Long> config = new ViewItemHibernateDao<>(NodeEntity.class);
        List<NodeEntity> listConfig = config.getAllConfig();
        ModbusType modbusType;
        for (NodeEntity entity : listConfig) {
            try {
                if (entity.getType().equalsIgnoreCase("rtu")) {
                    modbusType = new ModbusRtu(entity);
                    modbusType.getTypes(taskList, queue);
                }
                if (entity.getType().equalsIgnoreCase("tcp")) {
                    modbusType = new ModbusTcp(entity);
                    modbusType.getTypes(taskList, queue);
                }
            } catch (NullPointerException ex) {
                log.error("Can't get configuration for " + entity.getName());
            }
        }
    }
}
