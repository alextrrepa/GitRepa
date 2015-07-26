package org.webscada.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.log4j.Logger;
import org.webscada.controllers.tree_edit_delegation.*;
import org.webscada.dao.AbstractDao;
import org.webscada.dao.DaoConfig;
import org.webscada.model.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ModbusTreeEditController extends HttpServlet {
    private final static Logger log = Logger.getLogger(ModbusTreeEditController.class);
    Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().
            create();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Operation operation = new Operation(request, gson);
        Command command = FactoryOperation.getCommand(request, operation);
        DoOperation doOperation = new DoOperation(command);
        String commandResp = doOperation.makeCommand();

        log.trace("Ul id" + " :" + commandResp);

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.write(gson.toJson(commandResp));
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        Long id = Long.parseLong(request.getParameter("id"));
        log.trace(id);
        log.trace(type);

        String json = null;
        if (type.equalsIgnoreCase("node")) {
            log.trace("@@@@@Node@@@@@");
            AbstractDao<NodeEntity, Long> nodeDao = new DaoConfig<>(NodeEntity.class);
            NodeEntity entity = nodeDao.getById(id);
            json = gson.toJson(entity);
            /*String mtype = request.getParameter("mtype");
            AbstractDao<NodeEntity, Long> nodeDao = new DaoConfig<>(NodeEntity.class);
            if (mtype.equalsIgnoreCase("rtu")) {
                NodeEntity rtuNode = nodeDao.getById(id);
                json = gson.toJson(rtuNode);
            }
            if (mtype.equalsIgnoreCase("tcp")) {
                NodeEntity tcpNode = nodeDao.getById(id);
                json = gson.toJson(tcpNode);
            }*/
        }
        if (type.equalsIgnoreCase("device")) {
            AbstractDao<DeviceEntity, Long> deviceDao = new DaoConfig<>(DeviceEntity.class);
            DeviceEntity devEntity = deviceDao.getById(id);
            json = gson.toJson(devEntity);
        }
        if (type.equalsIgnoreCase("tag")) {
            AbstractDao<TagEntity, Long> tagDao = new DaoConfig<>(TagEntity.class);
            TagEntity tagEntity = tagDao.getById(id);
            json = gson.toJson(tagEntity);
        }

        log.trace(json);
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.write(json);
        out.close();
    }
}
