package org.webscada.dao;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.webscada.model.DeviceEntity;
import org.webscada.model.NodeEntity;
import org.webscada.model.TagEntity;
import org.webscada.model.tree.DeviceParams;
import org.webscada.model.tree.NodeParams;
import org.webscada.model.tree.TagParams;
import org.webscada.util.SessionUtil;

import java.util.ArrayList;
import java.util.List;

public class DaoTreeParams extends AbstractDao {
    private final static Logger log = Logger.getLogger(DaoTreeParams.class);

    @Override
    public List getAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<NodeParams> getTreeParams() {
        List<NodeParams> listTree = new ArrayList<>();
        Session session = SessionUtil.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query rtuQuery = session.createQuery("from NodeEntity as node where node.type = 'rtu' ");
            List<NodeEntity> rtuList = rtuQuery.list();
//            NodeParams nodeParams = null;

            for (NodeEntity nodeEntity : rtuList) {
                NodeParams nodeParams = new NodeParams();
                nodeParams.setTagType("node");
                nodeParams.setName(nodeEntity.getName());
                nodeParams.setId(nodeEntity.getId());
                nodeParams.setType(nodeEntity.getType());
                List<DeviceEntity> deviceList =  nodeEntity.getDeviceEntity();

                List<DeviceParams> devices = new ArrayList<>();

                for (DeviceEntity device : deviceList ) {
                    DeviceParams deviceParams = new DeviceParams();
                    deviceParams.setId(device.getId());
                    deviceParams.setTagType("device");
                    deviceParams.setName(device.getName());
                    List<TagEntity> tagList = device.getTagEntities();

                    List<TagParams> tags = new ArrayList<>();

                    for (TagEntity tag : tagList) {
                        TagParams tagParams = new TagParams();
                        tagParams.setTagType("tag");
                        tagParams.setName(tag.getName());
                        tagParams.setId(tag.getId());
                        tags.add(tagParams);
                    }
                    deviceParams.setTagList(tags);
                    devices.add(deviceParams);
                }
                nodeParams.setDeviceList(devices);
                listTree.add(nodeParams);
            }

            Query tcpQuery = session.createQuery("from NodeEntity as node where node.type = 'tcp' ");
            List<NodeEntity> tcpList = tcpQuery.list();
            for (NodeEntity nodeEntity : tcpList) {
                NodeParams nodeParams = new NodeParams();
                nodeParams.setTagType("node");
                nodeParams.setId(nodeEntity.getId());
                nodeParams.setType(nodeEntity.getType());
                nodeParams.setName(nodeEntity.getName());
                
                List<DeviceEntity> deviceList = nodeEntity.getDeviceEntity();
                
                List<DeviceParams> deviceParams = new ArrayList<>();

                for (DeviceEntity device : deviceList) {
                    DeviceParams devParams = new DeviceParams();
                    devParams.setId(device.getId());
                    devParams.setTagType("device");
                    devParams.setName(device.getName());

                    deviceParams.add(devParams);
                }
                nodeParams.setDeviceList(deviceParams);
                listTree.add(nodeParams);
            }
            
            transaction.commit();
        }catch (Exception e) {
            try {
                transaction.rollback();
            }catch (Exception ex) {
                log.error("Rollback transaction error", ex);
            }finally {
                if (session !=null) {
                    session.close();
                }
            }
        }finally {
            if (session !=null) {
                session.close();
            }
        }
        return listTree;
    }
}
