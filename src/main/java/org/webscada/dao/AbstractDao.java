package org.webscada.dao;

import javax.persistence.Entity;
import java.util.List;

public abstract class AbstractDao<T extends Entity> {
    public abstract List<T> getAll();
}
