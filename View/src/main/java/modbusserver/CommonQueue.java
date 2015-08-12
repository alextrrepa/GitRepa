package modbusserver;

import java.util.Map;
import java.util.concurrent.TransferQueue;

public class CommonQueue {
    private static TransferQueue<Map<String, Map<String, Float>>> queue;

    public static TransferQueue<Map<String, Map<String, Float>>> getQueue() {
        return queue;
    }

    public static void setQueue(TransferQueue<Map<String, Map<String, Float>>> queue) {
        CommonQueue.queue = queue;
    }
}
