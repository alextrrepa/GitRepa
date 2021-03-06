package admin.controllers;

import admin.controllers.tree_edit_delegation.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class ModbusTreeEditController extends HttpServlet {
    private Map<String, Command> commandMap = new HashMap<>();
    private Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().
            create();

    public ModbusTreeEditController() {
        Operation operation = new Operation(gson);
        commandMap.put("getAll", new GetAllOperation(operation));
        commandMap.put("addRtuNode", new AddRtuNodeOperation(operation));
        commandMap.put("addTcpNode", new AddTcpNodeOperation(operation));
        commandMap.put("addDevice", new AddDeviceOperation(operation));
        commandMap.put("addTag", new AddTagOperation(operation));
        commandMap.put("getNode", new GetNodeOperation(operation));
        commandMap.put("getDevice", new GetDeviceOperation(operation));
        commandMap.put("getTag", new GetTagOperation(operation));
        commandMap.put("deleteNode", new DeleteNodeOperation(operation));
        commandMap.put("deleteDevice", new DeleteDeviceOperation(operation));
        commandMap.put("deleteTag", new DeleteTagOperation(operation));
        commandMap.put("update", new UpdateOperation(operation));
        commandMap.put("rename", new RenameOperation(operation));
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
        String json = operation.makeCommand(request);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.write(json);
        out.close();
    }
}
