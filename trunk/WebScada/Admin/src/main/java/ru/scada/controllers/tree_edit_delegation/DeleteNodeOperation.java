package ru.scada.controllers.tree_edit_delegation;

import javax.servlet.http.HttpServletRequest;

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
