package controllers.tree_edit_delegation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public class GetNodeOperation implements Command {
    private Operation operation;

    public GetNodeOperation(Operation operation) {
        this.operation = operation;
    }

    @Override
    public String execute(HttpServletRequest request) throws ServletException {
        return operation.getNode(request);
    }
}
