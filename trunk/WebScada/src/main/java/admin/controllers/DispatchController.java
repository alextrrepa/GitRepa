package admin.controllers;

import admin.controllers.dispatch_delegation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DispatchController extends HttpServlet {
    private Map<String, Command> commandMap = new HashMap<>();

    public DispatchController() {
        Dispatch dispatch = new Dispatch();
        commandMap.put("modbusPage", new ModbusDispatch(dispatch));
        commandMap.put("adminPart", new AdminDispatch(dispatch));
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        performTask(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        performTask(request, response);
    }

    private void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        Command command = commandMap.get(action);
        DoDispatch doDispatch = new DoDispatch(command);
        doDispatch.makeDispatch(request, response);
    }
}
