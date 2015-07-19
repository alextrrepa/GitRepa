package org.webscada.controllers.tree_edit_delegation;

import javax.servlet.http.HttpServletRequest;

public class FactoryOperation {
    public static Command getCommand(HttpServletRequest request, Operation operation) {
        String action = request.getParameter("action");
        switch (action) {
            case "add":
                return new AddOperation(operation);
            case "delete":
                return new DeleteOperation(operation);
            case "subRtu":
                return new AddRtuOperation(operation);
            case "subTcp":
                return new AddTcpOperation(operation);
        }
        return null;
    }
}
