package org.webscada.controllers;

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
    private Map<String, Object> objectList;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("nodeType");
        String id = request.getParameter("type");
        log.trace(type + " " + id);

        if (type.equalsIgnoreCase("root")) {

            AbstractDao<NodeEntity, Long> nodeDao = new DaoConfig<>(NodeEntity.class);
            NodeEntity nodeEntity = new NodeEntity();
            nodeEntity.setName("Node");
            nodeDao.create(nodeEntity);
        }
        if (type.equalsIgnoreCase("node")) {
            AbstractDao<DeviceEntity, Long> deviceDao = new DaoConfig<>(DeviceEntity.class);
            DeviceEntity deviceEntity = new DeviceEntity();
            deviceEntity.setName("Device");
            deviceDao.create(deviceEntity);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        Long id = Long.parseLong(request.getParameter("id"));
        ObjectMapper mapper = new ObjectMapper();

        if (type.equalsIgnoreCase("node")) {
            String mtype = request.getParameter("mtype");
            jsonNodeParams(id, mtype);
        }
        if (type.equalsIgnoreCase("device")) {
            objectList = device(id);
        }
        if (type.equalsIgnoreCase("tag")) {
            objectList = tag(id);
        }
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        mapper.writeValue(out, objectList);
    }

    private void jsonNodeParams(Long id, String mtype) {
        if (mtype.equalsIgnoreCase("rtu")) {
            objectList = jsonRtuNodeParams(id);
        }
        if (mtype.equalsIgnoreCase("tcp")) {
            objectList = jsonTcpNodeParams(id);
        }
    }

    private Map<String, Object> jsonRtuNodeParams(Long id) {
        AbstractDao<NodeEntity, Long> nodeDao = new DaoConfig<>(NodeEntity.class);
        NodeEntity rtuNode = nodeDao.getById(id);
        RtuEntity rtuEntity = rtuNode.getRtuEntity();
        Map<String, Object> objectList = new LinkedHashMap<>();
        objectList.put("nodename", rtuNode.getName());
        objectList.put("modbustype", rtuNode.getType());
        objectList.put("port", rtuEntity.getPort());
        objectList.put("baudrate", rtuEntity.getBaudrate());
        objectList.put("databits", rtuEntity.getDatabits());
        objectList.put("parity", rtuEntity.getParity());
        objectList.put("stopbits", rtuEntity.getStopbits());
        objectList.put("retries", rtuEntity.getRetries());
        objectList.put("timeout", rtuEntity.getTimeout());
        objectList.put("period", rtuEntity.getPeriod());
        return objectList;
    }

    private Map<String, Object> jsonTcpNodeParams(Long id) {
        AbstractDao<NodeEntity, Long> nodeDao = new DaoConfig<>(NodeEntity.class);
        NodeEntity tcpNode = nodeDao.getById(id);
        TcpEntity tcpEntity = tcpNode.getTcpEntity();
        Map<String, Object> objectList = new LinkedHashMap<>();
        objectList.put("nodename", tcpNode.getName());
        objectList.put("modbustype", tcpNode.getType());
        objectList.put("ip", tcpEntity.getIp());
        objectList.put("port", tcpEntity.getPort());
        objectList.put("retries", tcpEntity.getRetries());
        objectList.put("timeout", tcpEntity.getTimeout());
        objectList.put("period", tcpEntity.getPeriod());
        return objectList;
    }

    private Map<String, Object> device(Long id) {
        AbstractDao<DeviceEntity, Long> deviceDao = new DaoConfig<>(DeviceEntity.class);
        DeviceEntity device = deviceDao.getById(id);
        Map<String, Object> objectList = new LinkedHashMap<>();
        objectList.put("devicename", device.getName());
        objectList.put("slaveid", device.getSlaveid());
        objectList.put("startoffset", device.getStartOffset());
        objectList.put("counts", device.getCounts());
        RegisterEntity registerEntity = device.getRegisterEntity();
        objectList.put("regtype", registerEntity.getName());
        return objectList;
    }

    private Map<String, Object> tag(Long id) {
        AbstractDao<TagEntity, Long> tagDao = new DaoConfig<>(TagEntity.class);
        TagEntity tagEntity = tagDao.getById(id);
        Map<String, Object> objectList = new LinkedHashMap<>();
        objectList.put("tagname", tagEntity.getName());
        objectList.put("realoffset", tagEntity.getRealOffset());
        DatatypeEntity dataType = tagEntity.getDatatypeEntity();
        objectList.put("datatype", dataType.getName());
        return objectList;
    }
}
