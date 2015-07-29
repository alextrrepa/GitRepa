package org.webscada.controllers.tree_edit_delegation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteOperation implements Command {
    private Operation operation;

    public DeleteOperation(Operation operation) {
        this.operation = operation;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return operation.delete(request, response);
    }
}
