package endpointserver;

import org.codehaus.jackson.map.ObjectMapper;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import java.io.IOException;
import java.util.Map;


public class ResultEncoder implements Encoder.Text<Map<String, Map<String, Float>>> {

    @Override
    public String encode(Map<String, Map<String, Float>> stringMap) throws EncodeException {
        String val = null;
        try {
            val = new ObjectMapper().writeValueAsString(stringMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return val;
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }

}
