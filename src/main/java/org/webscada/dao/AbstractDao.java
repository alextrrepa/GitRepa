package org.webscada.dao;

import org.webscada.model.NodeEntity;
import org.webscada.model.tree.NodeParams;

import java.util.List;

public abstract class AbstractDao<T> {
    public abstract List<NodeEntity> getAll();
    public abstract List<NodeParams> getTreeParams();
}
