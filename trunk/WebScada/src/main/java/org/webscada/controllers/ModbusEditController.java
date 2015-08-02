package org.webscada.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.log4j.Logger;
import org.webscada.controllers.tree_edit_delegation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class ModbusEditController extends HttpServlet {
    private final static Logger log = Logger.getLogger(ModbusEditController.class);
    private Map<String, Command> commandMap = new HashMap<>();
    private Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().
            create();

    public ModbusEditController() {
        Operation operation = new Operation(gson);
        commandMap.put("getAll", new GetAllOperation(operation));
        commandMap.put("addNode", new AddNodeOperation(operation));
        commandMap.put("addDevice", new AddDeviceOperation(operation));
        commandMap.put("addTag", new AddTagOperation(operation));
        commandMap.put("getNode", new GetNodeOperation(operation));
        commandMap.put("getDevice", new GetDeviceOperation(operation));
        commandMap.put("getTag", new GetTagOperation(operation));
        commandMap.put("deleteNode", new DeleteNodeOperation(operation));
        commandMap.put("deleteDevice", new DeleteDeviceOperation(operation));
        commandMap.put("deleteTag", new DeleteTagOperation(operation));
        commandMap.put("update", new UpdateOperation(operation));
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
        DoOperation operation = new DoOperation(command);
        String json = operation.makeCommand(request, response);
        log.trace(json);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.write(json);
        out.close();
    }
}
