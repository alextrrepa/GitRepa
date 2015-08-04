package org.webscada.controllers.tree_edit_delegation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteNodeOperation implements Command {
    private Operation operation;

    public DeleteNodeOperation(Operation operation) {
        this.operation = operation;
    }

    @Override
    public String execute(HttpServletRequest request) {
        return operation.deleteNode(request);
    }
}
