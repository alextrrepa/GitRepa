package org.webscada.dao;

import org.webscada.model.NodeEntity;

public class ConfigDaoFactory {
    public AbstractDao<NodeEntity> getDao(String type){
        /*switch (type){
            case "rtu": return new ConfigDaoRtu<>();
            case "tcp": return new ConfigDaoTcp<>();
        }*/
        return null;
    }
}
