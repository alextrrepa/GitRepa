package org.webscada.modbusserver;

public class NodeFactory {
    public Node node(String type) {
        switch (type) {
            case "rtu": return new NodeRtu();
            case "tcp": return new NodeTcp();
        }
        return null;
    }
}
