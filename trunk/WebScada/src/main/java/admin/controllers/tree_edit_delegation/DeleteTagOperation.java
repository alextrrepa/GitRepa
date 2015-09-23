package admin.controllers.tree_edit_delegation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public class DeleteTagOperation implements Command {
    private Operation operation;

    public DeleteTagOperation(Operation operation) {
        this.operation = operation;
    }

    @Override
    public String execute(HttpServletRequest request) throws ServletException {
        return operation.deleteTag(request);
    }
}
