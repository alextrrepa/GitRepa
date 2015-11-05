package data.controllers.data_delegation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DoOperation {
    private Command command;

    public DoOperation(Command command) {
        this.command = command;
    }

    public void makeCommand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        command.execute(request, response);
    }
}
