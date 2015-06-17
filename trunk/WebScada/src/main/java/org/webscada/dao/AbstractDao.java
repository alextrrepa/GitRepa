package org.webscada.dao;

import java.util.List;

public abstract class AbstractDao<T> {
    public abstract List<T> getAll();
}
