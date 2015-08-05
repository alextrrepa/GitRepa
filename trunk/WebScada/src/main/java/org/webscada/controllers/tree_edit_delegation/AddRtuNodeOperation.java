package org.webscada.controllers.tree_edit_delegation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddRtuNodeOperation implements Command {
    private Operation operation;

    public AddRtuNodeOperation(Operation operation) {
        this.operation = operation;
    }

    @Override
    public String execute(HttpServletRequest request) throws ServletException {
        return operation.addRtuNode(request);
    }
}
