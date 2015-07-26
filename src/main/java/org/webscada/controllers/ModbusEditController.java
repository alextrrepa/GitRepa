package org.webscada.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
import java.util.ArrayList;
import java.util.List;

public class ModbusEditController extends HttpServlet {
    private final static Logger log = Logger.getLogger(ModbusEditController.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<TreeElement> treeElements = new ArrayList<>();
        TreeElement root = new TreeElement("root", "#", "Сервер", "images/icn_server.png");
        treeElements.add(root);

        String json = null;
        Gson gson = new GsonBuilder().setPrettyPrinting().
                create();

        String action = request.getParameter("action");
        if (action.equalsIgnoreCase("getAll")) {
            AbstractDao<NodeEntity, Long> nodeDao = new DaoConfig<>(NodeEntity.class);
            List<NodeEntity> nodeList = nodeDao.getAllConfig();
            for (NodeEntity node : nodeList) {
                TreeElement nodeElem = new TreeElement("node" + Long.toString(node.getId()),
                        "root", node.getName(), "images/icn_node.png", Long.toString(node.getId()));
                treeElements.add(nodeElem);

                List<DeviceEntity> deviceList = node.getDeviceEntity();
                for (DeviceEntity device : deviceList) {
                    TreeElement deviceElem = new TreeElement(
                            "device" + Long.toString(device.getId()),
                            "node" + Long.toString(node.getId()), device.getName(),
                            "images/icn_device.png", Long.toString(device.getId()));
                    treeElements.add(deviceElem);

                    List<TagEntity> tagList = device.getTagEntities();
                    for (TagEntity tag : tagList) {
                        TreeElement tagElem = new TreeElement(
                                "tag" + Long.toString(tag.getId()),
                                "device" + Long.toString(device.getId()), tag.getName(),
                                "images/icn_tag.png", Long.toString(tag.getId()));
                        treeElements.add(tagElem);
                    }
                }
            }
            json = gson.toJson(treeElements);
        }
        log.trace(json);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.write(json);
        out.close();
    }
}
