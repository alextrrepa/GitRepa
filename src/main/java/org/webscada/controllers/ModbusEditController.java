package org.webscada.controllers;

import org.apache.log4j.Logger;
import org.webscada.dao.AbstractDao;
import org.webscada.dao.DaoConfig;
import org.webscada.model.NodeEntity;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ModbusEditController extends HttpServlet {
    private final static Logger log = Logger.getLogger(ModbusEditController.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action.equalsIgnoreCase("getAll")) {
            AbstractDao daoConfig = new DaoConfig(NodeEntity.class);
            List<NodeEntity> treeParams = daoConfig.getAllConfig();
            request.setAttribute("treeParams", treeParams);
        }
        RequestDispatcher rd = request.getRequestDispatcher("/modbus_page.jsp");
        rd.forward(request, response);
    }
}
