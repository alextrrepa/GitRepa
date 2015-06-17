package org.webscada.modbusserver;

import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TransferQueue;

public class ModbusBridge {
    private final static Logger log = Logger.getLogger(ModbusBridge.class);
    private NodeFactory factory = new NodeFactory();


    public ModbusBridge(Set<String> typeSet, List<ModbusTask> taskList,
                        TransferQueue<Map<String, Map<String, Float>>> queue) {
        getTypeConfig(typeSet,taskList, queue);
    }

    private void getTypeConfig(Set<String> typeSet, List<ModbusTask> taskList,
                               TransferQueue<Map<String, Map<String, Float>>> queue) {
        for (String type : typeSet) {
            Node node = factory.node(type);

            String nt = node.type();
            if (nt.equalsIgnoreCase("rtu")) {
                ModbusType rtuType = new ModbusRtu(node);
                rtuType.getTypes(taskList, queue);
            }
            /*if (nt.equalsIgnoreCase("tcp")) {
                ModbusType tcpType = new ModbusTcp(node);
                tcpType.getTypes(taskList, queue);
            }*/
        }
    }
}
