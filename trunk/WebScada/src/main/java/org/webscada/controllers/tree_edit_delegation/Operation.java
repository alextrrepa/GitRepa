package org.webscada.controllers.tree_edit_delegation;

import org.webscada.dao.AbstractDao;
import org.webscada.dao.DaoConfig;
import org.webscada.model.DeviceEntity;
import org.webscada.model.NodeEntity;
import org.webscada.model.TagEntity;

import javax.servlet.http.HttpServletRequest;

public class Operation {
    private HttpServletRequest request;

    public Operation(HttpServletRequest request) {
        this.request = request;
    }

    public void add() {
        String nodeType = request.getParameter("nodeType");
        if (nodeType.equalsIgnoreCase("node")) {
            addDevice();
        }
        if (nodeType.equalsIgnoreCase("device")) {
            addTag();
        }
    }

    private void addDevice() {
        AbstractDao<DeviceEntity, Long> deviceDao = new DaoConfig<>(DeviceEntity.class);
        Long id = Long.valueOf(request.getParameter("id"));
        DeviceEntity deviceEntity = new DeviceEntity();
        deviceEntity.setName("Device");

        NodeEntity nodeEntity = new NodeEntity();
        nodeEntity.setId(id);

        deviceEntity.setNodeEntity(nodeEntity);
        deviceDao.create(deviceEntity);
    }

    private void addTag() {
        AbstractDao<TagEntity, Long> tagDao = new DaoConfig<>(TagEntity.class);
        Long id = Long.valueOf(request.getParameter("id"));
        TagEntity tagEntity = new TagEntity();
        tagEntity.setName("Tag");

        DeviceEntity deviceEntity = new DeviceEntity();
        deviceEntity.setId(id);

        tagEntity.setDeviceEntity(deviceEntity);
        tagDao.create(tagEntity);
    }

    public void delete() {
        Long id = Long.valueOf(request.getParameter("id"));
        String nodeType = request.getParameter("nodeType");
        switch (nodeType) {
            case "node":
                AbstractDao<NodeEntity, Long> nodeDao = new DaoConfig<>(NodeEntity.class);
                nodeDao.delete(id);
                break;
            case "device":
                AbstractDao<DeviceEntity, Long> deviceDao = new DaoConfig<>(DeviceEntity.class);
                deviceDao.delete(id);
                break;
            case "tag":
                AbstractDao<TagEntity, Long> tagDao = new DaoConfig<>(TagEntity.class);
                tagDao.delete(id);
                break;
        }
    }

    public void addRtu() {
        AbstractDao<NodeEntity, Long> nodeDao = new DaoConfig<>(NodeEntity.class);
        NodeEntity nodeEntity = new NodeEntity();
        nodeEntity.setName("Node");
        nodeEntity.setType("rtu");
        nodeDao.create(nodeEntity);
    }

    public void addTcp() {
        AbstractDao<NodeEntity, Long> nodeDao = new DaoConfig<>(NodeEntity.class);
        NodeEntity nodeEntity = new NodeEntity();
        nodeEntity.setName("Node");
        nodeEntity.setType("tcp");
        nodeDao.create(nodeEntity);
    }
}
