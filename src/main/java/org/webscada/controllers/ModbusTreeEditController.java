package org.webscada.controllers;

import org.apache.log4j.Logger;
import org.webscada.dao.AbstractDao;
import org.webscada.dao.DaoConfig;
import org.webscada.model.DeviceEntity;
import org.webscada.model.NodeEntity;
import org.webscada.model.TagEntity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ModbusTreeEditController extends HttpServlet {
    private final static Logger log = Logger.getLogger(ModbusTreeEditController.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        Long id = Long.parseLong(request.getParameter("id"));

        if (type.equalsIgnoreCase("node")) {
            AbstractDao<NodeEntity, Long> nodeDao = new DaoConfig<>(NodeEntity.class);
            NodeEntity nodeEntity = nodeDao.getById(id);
            log.trace(nodeEntity.getName());
        }
        if (type.equalsIgnoreCase("device")) {
            AbstractDao<DeviceEntity, Long> deviceDao = new DaoConfig<>(DeviceEntity.class);
            DeviceEntity deviceEntity = deviceDao.getById(id);
            log.trace(deviceEntity.getName());
        }

        if (type.equalsIgnoreCase("tag")) {
            AbstractDao<TagEntity, Long> tagDao = new DaoConfig<>(TagEntity.class);
            TagEntity tagEntity = tagDao.getById(id);
            log.trace(tagEntity.getName());
        }

        PrintWriter out = response.getWriter();
    }
}
