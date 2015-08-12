package endpointserver;

import org.apache.log4j.Logger;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

@ServerEndpoint(value = "/monitor",
        encoders = { ResultEncoder.class }
)
public class ModbusEndpoint implements DataSourceListener {
    private final static Logger log = Logger.getLogger(ModbusEndpoint.class.getName());
//    private Session session;
//    private ServerEndpointConfig endpointConfig;
    private ModbusClientConsumer consumer;
    private static Set<Session> sessionSet;

    @OnOpen
    public void onOpen(Session session) throws IOException{
//        this.endpointConfig = (ServerEndpointConfig) config;
//        ModbusServerConfigurator configurator = (ModbusServerConfigurator) endpointConfig.getConfigurator();
//        this.session = session;
        sessionSet = session.getOpenSessions();
        this.consumer = new ModbusClientConsumer();
        this.consumer.addDataSourceListener(this);
        this.consumer.start();
    }

    public void handleNewData(Map<String, Map<String, Float>> json) {
        for (Session session : sessionSet){
            try {
                session.getBasicRemote().sendObject(json);
            } catch (IOException e) {
                log.error("IOexception Websocket", e.getCause());
            } catch (EncodeException e) {
                log.error("WebSocket Encode Error ", e.getCause());
            }
        }
    }

    @OnClose
    public void onClose(){
            log.trace("WebSocket Session Closed");
            consumer.stop();
    }

    @OnError
    public void onError(Throwable t){
        log.error("Some other error", t.fillInStackTrace());
    }
}