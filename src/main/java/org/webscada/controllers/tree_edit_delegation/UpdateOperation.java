package org.webscada.controllers.tree_edit_delegation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateOperation implements Command {
    private Operation operation;

    public UpdateOperation(Operation operation) {
        this.operation = operation;
    }

    @Override
    public String execute(HttpServletRequest request) throws ServletException {
        return operation.update(request);
    }


}
