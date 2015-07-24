package org.webscada.controllers.tree_edit_delegation;

import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.webscada.dao.AbstractDao;
import org.webscada.dao.DaoConfig;
import org.webscada.model.DeviceEntity;
import org.webscada.model.NodeEntity;
import org.webscada.model.TagEntity;

import javax.servlet.http.HttpServletRequest;

public class Operation {
    private final static Logger log = Logger.getLogger(Operation.class);
    private HttpServletRequest request;
    private Gson gson;

    public Operation(HttpServletRequest request, Gson gson) {
        this.request = request;
        this.gson = gson;
    }

    public String add() {
        String nodeType = request.getParameter("nodeType");
        if (nodeType.equalsIgnoreCase("node")) {
           return addDevice();
        }
        if (nodeType.equalsIgnoreCase("device")) {
           return addTag();
        }
        return null;
    }

    private String addDevice() {
        AbstractDao<DeviceEntity, Long> deviceDao = new DaoConfig<>(DeviceEntity.class);
        Long id = Long.valueOf(request.getParameter("id"));
        DeviceEntity deviceEntity = new DeviceEntity();
        deviceEntity.setName("Device");

        NodeEntity nodeEntity = new NodeEntity();
        nodeEntity.setId(id);

        deviceEntity.setNodeEntity(nodeEntity);
        deviceDao.create(deviceEntity);

        return gson.toJson(deviceEntity);
    }

    private String addTag() {
        AbstractDao<TagEntity, Long> tagDao = new DaoConfig<>(TagEntity.class);
        Long id = Long.valueOf(request.getParameter("id"));
        TagEntity tagEntity = new TagEntity();
        tagEntity.setName("Tag");

        DeviceEntity deviceEntity = new DeviceEntity();
        deviceEntity.setId(id);

        tagEntity.setDeviceEntity(deviceEntity);
        tagDao.create(tagEntity);

        Long tagId = tagEntity.getId();
        return gson.toJson(tagEntity);
    }

    public String delete() {
        Long id = Long.valueOf(request.getParameter("id"));
        String nodeType = request.getParameter("nodeType");
        switch (nodeType) {
            case "node":
                AbstractDao<NodeEntity, Long> nodeDao = new DaoConfig<>(NodeEntity.class);
                nodeDao.delete(id);
                return "nodelist";
            case "device":
                AbstractDao<DeviceEntity, Long> deviceDao = new DaoConfig<>(DeviceEntity.class);
                deviceDao.delete(id);
                return "devicelist";
            case "tag":
                AbstractDao<TagEntity, Long> tagDao = new DaoConfig<>(TagEntity.class);
                tagDao.delete(id);
                return "taglist";
        }
        return null;
    }

    public String addRtu() {
        System.out.println("Add Rtu");
        AbstractDao<NodeEntity, Long> nodeDao = new DaoConfig<>(NodeEntity.class);
        NodeEntity nodeEntity = new NodeEntity();
        nodeEntity.setName("Node");
        nodeEntity.setType("rtu");
        nodeDao.create(nodeEntity);

        Long nodeId = nodeEntity.getId();
        return gson.toJson(nodeEntity);
    }

    public String addTcp() {
        AbstractDao<NodeEntity, Long> nodeDao = new DaoConfig<>(NodeEntity.class);
        NodeEntity nodeEntity = new NodeEntity();
        nodeEntity.setName("Node");
        nodeEntity.setType("tcp");
        nodeDao.create(nodeEntity);

        Long nodeId = nodeEntity.getId();
        return gson.toJson(nodeEntity);
    }
}