package ru.scada.endpointserver;

import java.util.Map;

public interface DataSourceListener {
    void handleNewData(Map<String, Map<String, Float>> json);
}
