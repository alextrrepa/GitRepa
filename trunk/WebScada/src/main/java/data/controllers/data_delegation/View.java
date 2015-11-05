package data.controllers.data_delegation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class View implements Command {
    private Operation operation;

    public View(Operation operation) {
        this.operation = operation;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        operation.viewingData(request, response);
    }
}
