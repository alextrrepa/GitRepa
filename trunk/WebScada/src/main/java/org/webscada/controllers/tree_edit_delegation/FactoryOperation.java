package org.webscada.controllers.tree_edit_delegation;

import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;

public class FactoryOperation {
    private final static Logger log = Logger.getLogger(FactoryOperation.class);

    public static Command getCommand(HttpServletRequest request, Operation operation) {
        String action = request.getParameter("action");
        switch (action) {
            case "rename_node":
                return new AddOperation(operation);
            case "delete_node":
                return new DeleteOperation(operation);
            /*case "subRtu":
                return new AddRtuOperation(operation);
            case "subTcp":
                return new AddTcpOperation(operation);*/
        }
        return null;
    }
}
