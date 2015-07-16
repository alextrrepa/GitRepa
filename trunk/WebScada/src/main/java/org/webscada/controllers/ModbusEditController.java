package org.webscada.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.webscada.dao.AbstractDao;
import org.webscada.dao.DaoConfig;
import org.webscada.model.DeviceEntity;
import org.webscada.model.NodeEntity;
import org.webscada.model.TagEntity;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ModbusEditController extends HttpServlet {
    private final static Logger log = Logger.getLogger(ModbusEditController.class);


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().
                create();

        String json = null;
        if (action.equalsIgnoreCase("getAll")) {
            AbstractDao<NodeEntity, Long> nodeDao = new DaoConfig<>(NodeEntity.class);
            List<NodeEntity> nodeList = nodeDao.getAllConfig();
            json = gson.toJson(nodeList);
//            System.out.println(json);
        }
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.write(json);
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*
        String action = request.getParameter("action");
        if (action.equalsIgnoreCase("getAll")) {
            AbstractDao daoConfig = new DaoConfig(NodeEntity.class);
            List<NodeEntity> treeParams = daoConfig.getAllConfig();
            request.setAttribute("treeParams", treeParams);
        }
        RequestDispatcher rd = request.getRequestDispatcher("/modbus_page.jsp");
         rd.forward(request, response);
*/
        response.sendRedirect("./modbus_page.jsp");
    }
}
