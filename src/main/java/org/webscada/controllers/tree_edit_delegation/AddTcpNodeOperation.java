package org.webscada.controllers.tree_edit_delegation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public class AddTcpNodeOperation implements Command {
    private Operation operation;

    public AddTcpNodeOperation(Operation operation) {
        this.operation = operation;
    }

    @Override
    public String execute(HttpServletRequest request) throws ServletException {
        return operation.addTcpNode(request);
    }
}
