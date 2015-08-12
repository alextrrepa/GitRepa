package controllers.tree_edit_delegation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public class GetDeviceOperation implements Command {
    private Operation operation;

    public GetDeviceOperation(Operation operation) {
        this.operation = operation;
    }

    @Override
    public String execute(HttpServletRequest request) throws ServletException {
        return operation.getDevice(request);
    }
}
