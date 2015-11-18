package admin.controllers;

import admin.controllers.admin_delegation.Accounts;
import admin.controllers.admin_delegation.Action;
import admin.controllers.admin_delegation.Command;
import admin.controllers.admin_delegation.DoAction;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AdminController extends HttpServlet {
    private final static Logger log = Logger.getLogger(AdminController.class);
    private Map<String, Command> commandMap = new HashMap<>();

    public AdminController() {
        Action action = new Action();
        commandMap.put("accounts", new Accounts(action));
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        performTask(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        performTask(request, response);
    }

    private void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            request.getRequestDispatcher("admin/admin.jsp").forward(request, response);
        }
        Command command = commandMap.get(action);
        DoAction doAction = new DoAction(command);
        doAction.makeAction(request, response);
    }
}
