package ru.scada.controllers.tree_edit_delegation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public class DeleteDeviceOperation implements Command {
    private Operation operation;

    public DeleteDeviceOperation(Operation operation) {
        this.operation = operation;
    }

    @Override
    public String execute(HttpServletRequest request) throws ServletException {
        return operation.deleteDevice(request);
    }
}
