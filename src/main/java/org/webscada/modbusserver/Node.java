package org.webscada.modbusserver;

import org.webscada.entities.NodeEntity;
import java.util.List;

public interface Node {
    List<NodeEntity> getNodes();
    String type();
}
