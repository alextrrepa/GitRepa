package org.webscada.controllers.tree_edit_delegation;

import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.webscada.controllers.TreeElement;
import org.webscada.dao.AbstractDao;
import org.webscada.dao.DaoConfig;
import org.webscada.model.DeviceEntity;
import org.webscada.model.NodeEntity;
import org.webscada.model.RtuEntity;
import org.webscada.model.TagEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class Operation {
    private final static Logger log = Logger.getLogger(Operation.class);
    private Gson gson;

    public Operation(Gson gson) {
        this.gson = gson;
    }

    public String addNode(HttpServletRequest request, HttpServletResponse response) {
        AbstractDao<NodeEntity, Long> nodeDao = new DaoConfig<>(NodeEntity.class);
        NodeEntity nodeEntity = new NodeEntity();
        nodeEntity.setName(request.getParameter("nodeName"));
        nodeDao.create(nodeEntity);
        TreeElement treeElement = new TreeElement("node" + Long.toString(nodeEntity.getId()),
                "root", nodeEntity.getName(),
                "images/icn_node.png", Long.toString(nodeEntity.getId()));
        return gson.toJson(treeElement);
    }

    public String addDevice(HttpServletRequest request, HttpServletResponse response) {
        AbstractDao<DeviceEntity, Long> deviceDao = new DaoConfig<>(DeviceEntity.class);
        Long id = Long.valueOf(request.getParameter("id"));
        DeviceEntity deviceEntity = new DeviceEntity();
        deviceEntity.setName(request.getParameter("nodeName"));
        NodeEntity nodeEntity = new NodeEntity();
        nodeEntity.setId(id);
        deviceEntity.setNodeEntity(nodeEntity);
        deviceDao.create(deviceEntity);
        return gson.toJson(deviceEntity);
    }

    public String addTag(HttpServletRequest request, HttpServletResponse response) {
        AbstractDao<TagEntity, Long> tagDao = new DaoConfig<>(TagEntity.class);
        Long id = Long.valueOf(request.getParameter("id"));
        TagEntity tagEntity = new TagEntity();
        tagEntity.setName(request.getParameter("nodeName"));
        DeviceEntity deviceEntity = new DeviceEntity();
        deviceEntity.setId(id);
        tagEntity.setDeviceEntity(deviceEntity);
        tagDao.create(tagEntity);
        return gson.toJson(tagEntity);
    }

    public String deleteNode(HttpServletRequest request, HttpServletResponse response) {
        AbstractDao<NodeEntity, Long> nodeDao = new DaoConfig<>(NodeEntity.class);
        Long id = Long.valueOf(request.getParameter("id"));
        nodeDao.delete(id);
        return gson.toJson("success");
    }

    public String deleteDevice(HttpServletRequest request, HttpServletResponse response) {
        AbstractDao<DeviceEntity, Long> deviceDao = new DaoConfig<>(DeviceEntity.class);
        Long id = Long.valueOf(request.getParameter("id"));
        deviceDao.delete(id);
        return gson.toJson("success");
    }

    public String deleteTag(HttpServletRequest request, HttpServletResponse response) {
        AbstractDao<TagEntity, Long> tagDao = new DaoConfig<>(TagEntity.class);
        Long id = Long.valueOf(request.getParameter("id"));
        tagDao.delete(id);
        return gson.toJson("success");
    }

    public String getAll(HttpServletRequest request, HttpServletResponse response) {
        List<TreeElement> treeElements = new ArrayList<>();
        TreeElement root = new TreeElement("root", "#", "Сервер", "images/icn_server.png");
        treeElements.add(root);

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
        return gson.toJson(treeElements);
    }

    public String getNode(HttpServletRequest request, HttpServletResponse response) {
        AbstractDao<NodeEntity, Long> nodeDao = new DaoConfig<>(NodeEntity.class);
        Long id = Long.valueOf(request.getParameter("id"));
        NodeEntity nodeEntity = nodeDao.getById(id);
        return gson.toJson(nodeEntity);
    }

    public String getDevice(HttpServletRequest request, HttpServletResponse response) {
        AbstractDao<DeviceEntity, Long> deviceDao = new DaoConfig<>(DeviceEntity.class);
        Long id = Long.valueOf(request.getParameter("id"));
        DeviceEntity devEntity = deviceDao.getById(id);
        return gson.toJson(devEntity);
    }

    public String getTag(HttpServletRequest request, HttpServletResponse response) {
        AbstractDao<TagEntity, Long> tagDao = new DaoConfig<>(TagEntity.class);
        Long id = Long.valueOf(request.getParameter("id"));
        TagEntity tagEntity = tagDao.getById(id);
        return gson.toJson(tagEntity);
    }

    public String update(HttpServletRequest request, HttpServletResponse response) {
        String type = request.getParameter("type");
        switch (type) {
            case "node":
                return updateNode(request);
            case "device":
                updateDevice(request);
                break;
            case "tag":
                updateTag(request);
                break;
        }
        return "Success";
    }

    private String updateNode(HttpServletRequest request) {
        String modbusType = request.getParameter("modbustype");
        if (modbusType.equalsIgnoreCase("rtu")) {
            AbstractDao<NodeEntity, Long> rtuDao = new DaoConfig<>(NodeEntity.class);
            Long id = Long.valueOf(request.getParameter("id"));
            NodeEntity node = rtuDao.getById(id);
            node.setName(request.getParameter("nodename"));
            node.setType(request.getParameter("modbustype"));
            RtuEntity rtu = node.getRtuEntity();
            rtu.setPort(request.getParameter("port"));
            rtu.setBaudrate(Integer.valueOf(request.getParameter("baudrate")));
            rtu.setDatabits(Integer.valueOf(request.getParameter("databits")));
            rtu.setParity(Integer.valueOf(request.getParameter("parity")));
            rtu.setStopbits(Integer.valueOf(request.getParameter("stopbits")));
            rtu.setRetries(Integer.valueOf(request.getParameter("retries")));
            rtu.setTimeout(Integer.valueOf(request.getParameter("timeout")));
            rtu.setPeriod(Integer.valueOf(request.getParameter("period")));
            rtuDao.update(node);

            NodeEntity nodeAfter = rtuDao.getById(id);
            return gson.toJson(nodeAfter);
        }
        return null;
    }

    private void updateDevice(HttpServletRequest request) {

    }

    private void updateTag(HttpServletRequest request) {

    }
}
