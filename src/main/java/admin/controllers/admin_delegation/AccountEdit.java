package admin.controllers.admin_delegation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AccountEdit implements Command {
    private Action action;

    public AccountEdit(Action action) {
        this.action = action;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        action.getUser(request, response);
    }
}
