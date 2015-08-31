package ru.scada.controllers.tree_edit_delegation;

import com.google.gson.Gson;
import ru.scada.dao.GenericDao;
import ru.scada.dao.ItemDAO;
import ru.scada.dao.ItemDAOHibernate;
import ru.scada.model.*;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class Operation {
    private final static Logger log = Logger.getLogger(Operation.class);
    private Gson gson;

    public Operation(Gson gson) {
        this.gson = gson;
    }

    public String addRtuNode(HttpServletRequest request) {
        GenericDao<NodeEntity, Long> nodeDao = new ItemDAOHibernate<>(NodeEntity.class);
        NodeEntity nodeEntity = new NodeEntity();
        nodeEntity.setName(request.getParameter("nodeName"));
        nodeEntity.setType(request.getParameter("mtype"));
        TreeElement treeElement;
        try {
            nodeDao.create(nodeEntity);
            RtuEntity rtuEntity = new RtuEntity();
            rtuEntity.setNodeEntity(nodeEntity);
            GenericDao<RtuEntity, Long> rtuDao = new ItemDAOHibernate<>(RtuEntity.class);
            rtuDao.create(rtuEntity);
            treeElement = new TreeElement("node" + Long.toString(nodeEntity.getId()),
                    "root", nodeEntity.getName(),
                    "images/icn_node.png", Long.toString(nodeEntity.getId()));
            return gson.toJson(treeElement);
        } catch (Exception e) {
            e.printStackTrace();
            return gson.toJson("fail");
        }
    }

    public String addTcpNode(HttpServletRequest request) {
        GenericDao<NodeEntity, Long> nodeDao = new ItemDAOHibernate<>(NodeEntity.class);
        NodeEntity nodeEntity = new NodeEntity();
        nodeEntity.setName(request.getParameter("nodeName"));
        nodeEntity.setType(request.getParameter("mtype"));
        TreeElement treeElement;
        try {
            nodeDao.create(nodeEntity);
            TcpEntity tcpEntity = new TcpEntity();
            tcpEntity.setNodeEntity(nodeEntity);
            GenericDao<TcpEntity, Long> tcpDao = new ItemDAOHibernate<>(TcpEntity.class);
            tcpDao.create(tcpEntity);

            treeElement = new TreeElement("node" + Long.toString(nodeEntity.getId()),
                    "root", nodeEntity.getName(),
                    "images/icn_node.png", Long.toString(nodeEntity.getId()));
            return gson.toJson(treeElement);
        }catch (Exception e) {
            return gson.toJson("fail");
        }
    }

    public String addDevice(HttpServletRequest request) {
        GenericDao<DeviceEntity, Long> deviceDao = new ItemDAOHibernate<>(DeviceEntity.class);
        Long id = Long.valueOf(request.getParameter("id"));

        DeviceEntity deviceEntity = new DeviceEntity();
        deviceEntity.setName(request.getParameter("nodeName"));

        NodeEntity nodeEntity = new NodeEntity();
        nodeEntity.setId(id);
        deviceEntity.setNodeEntity(nodeEntity);
        try {
            deviceDao.create(deviceEntity);
            return gson.toJson(deviceEntity);
        } catch (Exception e) {
            return gson.toJson("fail");
        }
    }

    public String addTag(HttpServletRequest request) {
        GenericDao<TagEntity, Long> tagDao = new ItemDAOHibernate<>(TagEntity.class);
        Long id = Long.valueOf(request.getParameter("id"));
        TagEntity tagEntity = new TagEntity();
        tagEntity.setName(request.getParameter("nodeName"));
        DeviceEntity deviceEntity = new DeviceEntity();
        deviceEntity.setId(id);
        tagEntity.setDeviceEntity(deviceEntity);
        try {
            tagDao.create(tagEntity);
            return gson.toJson(tagEntity);
        } catch (Exception e) {
            return gson.toJson("fail");
        }
    }

    public String deleteNode(HttpServletRequest request) {
        GenericDao<NodeEntity, Long> nodeDao = new ItemDAOHibernate<>(NodeEntity.class);
        Long id = Long.valueOf(request.getParameter("id"));
        try {
            nodeDao.delete(id);
            return gson.toJson("success");
        } catch (Exception e) {
            return gson.toJson("fail");
        }
    }

    public String deleteDevice(HttpServletRequest request) {
        GenericDao<DeviceEntity, Long> deviceDao = new ItemDAOHibernate<>(DeviceEntity.class);
        Long id = Long.valueOf(request.getParameter("id"));
        try {
            deviceDao.delete(id);
            return gson.toJson("success");
        } catch (Exception e) {
            return gson.toJson("fail");
        }
    }

    public String deleteTag(HttpServletRequest request) {
        GenericDao<TagEntity, Long> tagDao = new ItemDAOHibernate<>(TagEntity.class);
        Long id = Long.valueOf(request.getParameter("id"));
        try {
            tagDao.delete(id);
            return gson.toJson("success");
        } catch (Exception e) {
            return gson.toJson("fail");
        }
    }

    public String getAll(HttpServletRequest request) {
        List<TreeElement> treeElements = new ArrayList<>();
        TreeElement root = new TreeElement("root", "#", "Сервер", "images/icn_server.png");
        treeElements.add(root);

        GenericDao<NodeEntity, Long> nodeDao = new ItemDAOHibernate<>(NodeEntity.class);
        try {
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
        } catch (Exception e) {
            return gson.toJson("fail");
        }
    }

    public String getNode(HttpServletRequest request) {
        GenericDao<NodeEntity, Long> nodeDao = new ItemDAOHibernate<>(NodeEntity.class);
        Long id = Long.valueOf(request.getParameter("id"));
        NodeEntity nodeEntity;
        try {
            nodeEntity = nodeDao.getById(id);
            return gson.toJson(nodeEntity);
        } catch (Exception e) {
            return gson.toJson("fail");
        }
    }

    public String getDevice(HttpServletRequest request) {
        GenericDao<DeviceEntity, Long> deviceDao = new ItemDAOHibernate<>(DeviceEntity.class);
        Long id = Long.valueOf(request.getParameter("id"));
        DeviceEntity devEntity;
        try {
            devEntity = deviceDao.getById(id);
            return gson.toJson(devEntity);
        } catch (Exception e) {
            return gson.toJson("fail");
        }
    }

    public String getTag(HttpServletRequest request) {
        GenericDao<TagEntity, Long> tagDao = new ItemDAOHibernate<>(TagEntity.class);
        Long id = Long.valueOf(request.getParameter("id"));
        TagEntity tagEntity;
        try {
            tagEntity = tagDao.getById(id);
            return gson.toJson(tagEntity);
        } catch (Exception e) {
            return gson.toJson("fail");
        }
    }

    public String update(HttpServletRequest request) {
        String type = request.getParameter("type");
        log.trace(type);
        switch (type) {
            case "node":
                return updateNode(request);
            case "device":
                return updateDevice(request);
            case "tag":
                return updateTag(request);
        }
        return null;
    }

    public String rename(HttpServletRequest request) {
        String type = request.getParameter("type");
        log.trace(type);
        switch (type) {
            case "node":
                return renameNode(request);
            case "device":
                return renameDevice(request);
            case "tag":
                return renameTag(request);
        }
        return null;
    }

    private String updateNode(HttpServletRequest request) {
        String modbusType = request.getParameter("modbustype");
        Long id = Long.valueOf(request.getParameter("id"));
        if (modbusType.equalsIgnoreCase("rtu")) {
            GenericDao<NodeEntity, Long> rtuDao = new ItemDAOHibernate<>(NodeEntity.class);
            NodeEntity node;
            try {
                node = rtuDao.getById(id);
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
                return gson.toJson("success");
            } catch (Exception e) {
                e.printStackTrace();
                return gson.toJson("fail");
            }
        }

        if (modbusType.equalsIgnoreCase("tcp")) {
            GenericDao<NodeEntity, Long> tcpDao = new ItemDAOHibernate<>(NodeEntity.class);
            NodeEntity node;
            try {
                node = tcpDao.getById(id);
                node.setName(request.getParameter("nodename"));
                node.setType(request.getParameter("modbustype"));
                TcpEntity tcp = node.getTcpEntity();
                tcp.setIp(request.getParameter("ip"));
                tcp.setPort(Integer.valueOf(request.getParameter("port")));
                tcp.setRetries(Integer.valueOf(request.getParameter("retries")));
                tcp.setTimeout(Integer.valueOf(request.getParameter("timeout")));
                tcp.setPeriod(Integer.valueOf(request.getParameter("period")));
                tcpDao.update(node);
                return gson.toJson("success");
            } catch (Exception e) {
                e.printStackTrace();
                return gson.toJson("fail");
            }
        }
        return null;
    }

    private String updateDevice(HttpServletRequest request) {
        GenericDao<DeviceEntity, Long> deviceDao = new ItemDAOHibernate<>(DeviceEntity.class);
        Long id = Long.valueOf(request.getParameter("id"));
        DeviceEntity device;
        try {
            device = deviceDao.getById(id);
            device.setName(request.getParameter("devicename"));
            device.setSlaveid(Integer.valueOf(request.getParameter("slaveid")));
            device.setStartOffset(Integer.parseInt(request.getParameter("startoffset")));
            device.setCounts(Integer.parseInt(request.getParameter("counts")));

            String regtype = request.getParameter("regtype");
            ItemDAO<RegisterEntity, Long> regDao = new ItemDAOHibernate<>(RegisterEntity.class);
            RegisterEntity regEntity = regDao.findRegByValue(Integer.valueOf(regtype));
            device.setRegisterEntity(regEntity);
            deviceDao.update(device);
            return gson.toJson("success");
        } catch (Exception e) {
            return gson.toJson("fail");
        }
    }

    private String updateTag(HttpServletRequest request) {
        GenericDao<TagEntity, Long> tagDao = new ItemDAOHibernate<>(TagEntity.class);
        Long id = Long.valueOf(request.getParameter("id"));
        TagEntity tag;
        try {
            tag = tagDao.getById(id);
            tag.setName(request.getParameter("tagname"));
            tag.setRealOffset(Integer.valueOf(request.getParameter("realoffset")));

            ItemDAO<DatatypeEntity, Long> datDao = new ItemDAOHibernate<>(DatatypeEntity.class);
            String datType = request.getParameter("datatype");
            DatatypeEntity dataEntity = datDao.findDataByValue(Integer.valueOf(datType));
            tag.setDatatypeEntity(dataEntity);
            tagDao.update(tag);
            return gson.toJson("success");
        } catch (Exception e) {
            return gson.toJson("fail");
        }
    }

    private String renameNode(HttpServletRequest request) {
        GenericDao<NodeEntity, Long> nodeDao = new ItemDAOHibernate<>(NodeEntity.class);
        Long id = Long.valueOf(request.getParameter("id"));
        log.trace(id);
        NodeEntity node;
        try {
            node = nodeDao.getById(id);
            node.setName(request.getParameter("text"));
            log.trace(request.getParameter("text"));
            nodeDao.update(node);
            NodeEntity nodeAfter = nodeDao.getById(id);
            return gson.toJson(nodeAfter.getName());
        } catch (Exception e) {
            return gson.toJson("fail");
        }
    }

    private String renameDevice(HttpServletRequest request) {
        GenericDao<DeviceEntity, Long> deviceDao = new ItemDAOHibernate<>(DeviceEntity.class);
        Long id = Long.valueOf(request.getParameter("id"));
        DeviceEntity device;
        try {
            device = deviceDao.getById(id);
            device.setName(request.getParameter("text"));
            deviceDao.update(device);
            DeviceEntity devAfter = deviceDao.getById(id);
            return gson.toJson(devAfter.getName());
        } catch (Exception e) {
            return gson.toJson("fail");
        }
    }

    private String renameTag(HttpServletRequest request) {
        GenericDao<TagEntity, Long> tagDao = new ItemDAOHibernate<>(TagEntity.class);
        Long id = Long.valueOf(request.getParameter("id"));
        TagEntity tag;
        try {
            tag = tagDao.getById(id);
            tag.setName(request.getParameter("text"));
            tagDao.update(tag);
            TagEntity tagAfter = tagDao.getById(id);
            return gson.toJson(tagAfter.getName());
        } catch (Exception e) {
            return gson.toJson("fail");
        }
    }
}