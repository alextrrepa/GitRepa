import modbusserver.ModbusBridge;
import modbusserver.ModbusTask;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

public class DaoTest {

    @Test
    public void testGet() {
        List<ModbusTask> taskList = new ArrayList<>();
        TransferQueue<Map<String, Map<String, Float>>> queue = new LinkedTransferQueue<>();
        new ModbusBridge(taskList, queue);
        System.out.println(queue.size());
    }
}
