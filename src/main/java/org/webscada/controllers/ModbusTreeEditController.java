package org.webscada.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.webscada.dao.AbstractDao;
import org.webscada.dao.DaoConfig;
import org.webscada.model.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

public class ModbusTreeEditController extends HttpServlet {
    private final static Logger log = Logger.getLogger(ModbusTreeEditController.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nodeType = request.getParameter("nodeType");
        String type = request.getParameter("type");
        Long id = Long.parseLong(request.getParameter("id"));

        if (nodeType.equalsIgnoreCase("root")) {
            AbstractDao<NodeEntity, Long> nodeDao = new DaoConfig<>(NodeEntity.class);
            if (type.equalsIgnoreCase("rtu")) {
                NodeEntity nodeEntity = new NodeEntity();
                nodeEntity.setName("Node");
                nodeEntity.setType("rtu");
                nodeDao.create(nodeEntity);
            }
            if (type.equalsIgnoreCase("tcp")) {
                NodeEntity nodeEntity = new NodeEntity();
                nodeEntity.setName("Node");
                nodeEntity.setType("tcp");
                nodeDao.create(nodeEntity);
            }
        }

        if (nodeType.equalsIgnoreCase("node")) {
            AbstractDao<DeviceEntity, Long> deviceDao = new DaoConfig<>(DeviceEntity.class);
            DeviceEntity deviceEntity = new DeviceEntity();
            deviceEntity.setName("Device");

            NodeEntity nodeEntity = new NodeEntity();
            nodeEntity.setId(id);

            deviceEntity.setNodeEntity(nodeEntity);
            deviceDao.create(deviceEntity);
        }

        if (nodeType.equalsIgnoreCase("device")) {
            AbstractDao<TagEntity, Long> tagDao = new DaoConfig<>(TagEntity.class);
            TagEntity tagEntity = new TagEntity();
            tagEntity.setName("Tag");

            DeviceEntity deviceEntity = new DeviceEntity();
            deviceEntity.setId(id);

            tagEntity.setDeviceEntity(deviceEntity);
            tagDao.create(tagEntity);
        }

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> objectMap = new LinkedHashMap<>();
        objectMap.put("status", "success");
        mapper.writeValue(out, objectMap);
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        Long id = Long.parseLong(request.getParameter("id"));
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().
                create();
        String json = null;
        if (type.equalsIgnoreCase("node")) {
            String mtype = request.getParameter("mtype");
            AbstractDao<NodeEntity, Long> nodeDao = new DaoConfig<>(NodeEntity.class);
            if (mtype.equalsIgnoreCase("rtu")) {
                NodeEntity rtuNode = nodeDao.getById(id);
//                RtuEntity rtuEntity = rtuNode.getRtuEntity();
                json = gson.toJson(rtuNode);
            }
            if (mtype.equalsIgnoreCase("tcp")) {
                NodeEntity tcpNode = nodeDao.getById(id);
//                TcpEntity tcpEntity = tcpNode.getTcpEntity();
                json = gson.toJson(tcpNode);
            }
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
