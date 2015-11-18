package admin.controllers.admin_delegation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DoAction {
    private Command command;

    public DoAction(Command command) {
        this.command = command;
    }

    public void makeAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        command.execute(request, response);
    }
}
