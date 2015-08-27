package ru.scada.endpointserver;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import java.util.Map;

public class ResultEncoder implements Encoder.Text<Map<String, Map<String, Float>>> {

    @Override
    public String encode(Map<String, Map<String, Float>> stringMap) throws EncodeException {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(stringMap);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }

}
