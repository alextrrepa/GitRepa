package org.webscada.controllers.tree_edit_delegation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetAllOperation implements Command {
    private Operation operation;

    public GetAllOperation(Operation operation) {
        this.operation = operation;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        return operation.getAll(request, response);
    }
}
