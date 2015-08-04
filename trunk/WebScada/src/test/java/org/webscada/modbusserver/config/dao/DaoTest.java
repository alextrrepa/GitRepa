package org.webscada.modbusserver.config.dao;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.webscada.controllers.tree_edit_delegation.Operation;
import org.webscada.dao.*;
import org.webscada.model.NodeEntity;
import org.webscada.model.RegisterEntity;
import org.webscada.model.RtuEntity;

public class DaoTest {
    private final static Logger log = Logger.getLogger(DaoTest.class);

    @Test
    public void testAbstractDao() {
        ItemDAO<RegisterEntity, Long> regDao = new ItemDAOHibernate<>(RegisterEntity.class);

    }
}
