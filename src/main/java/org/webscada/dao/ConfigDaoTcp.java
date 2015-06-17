package org.webscada.dao;

import javax.persistence.Entity;
import java.util.List;

public class ConfigDaoTcp<T extends Entity> extends AbstractDao<T> {
    @Override
    public List<T> getAll() {
        return null;
    }
}
