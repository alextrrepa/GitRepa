package org.webscada.controllers;

import org.apache.log4j.Logger;
import org.webscada.dao.AbstractDao;
import org.webscada.dao.ConfigDaoFactory;
import org.webscada.entities.NodeEntity;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
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
        AbstractDao<NodeEntity> getType = null;
        ConfigDaoFactory configFactory = new ConfigDaoFactory();
        getType = configFactory.getDao("rtu");
        List<NodeEntity> rtuEntities = getType.getAll();
        ServletContext sc = getServletContext();
        RequestDispatcher rd = sc.getRequestDispatcher("/modbus_page.jsp");
        request.setAttribute("rtuEntities", rtuEntities);
        rd.forward(request, response);
    }
}
