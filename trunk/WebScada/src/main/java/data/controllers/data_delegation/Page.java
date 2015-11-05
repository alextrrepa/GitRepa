package data.controllers.data_delegation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Page implements Command {
    private Operation operation;

    public Page(Operation operation) {
        this.operation = operation;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        operation.getIndexPage(request, response);
    }
}
