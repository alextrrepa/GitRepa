package admin.controllers.dispatch_delegation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DoDispatch {
    private Command command;

    public DoDispatch(Command command) {
        this.command = command;
    }

    public void makeDispatch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        command.execute(request, response);
    }
}
