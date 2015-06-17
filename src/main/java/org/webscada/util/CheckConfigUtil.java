package org.webscada.util;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.webscada.modbusserver.ConfigException;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class CheckConfigUtil {
    private final static Logger log = Logger.getLogger(CheckConfigUtil.class);
    private static Set<String> typeList = new LinkedHashSet<>();

    public static Set<String> checkConfig() {
        try {
            getTypes();
        } catch (ConfigException e) {
            log.error("Config error", e);
        }
        return typeList;
    }

    private static void getTypes() throws ConfigException {
        Session session = SessionUtil.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("select type from NodeEntity");
            List<String> tempList = query.list();

            for (int i=0; i< tempList.size(); i++) {
                String val = tempList.get(i);
                if (val == null) {
                    continue;
                } else {
                    typeList.add(val);
                }
            }
        }catch (Exception ex){
            try {
                transaction.rollback();
            }catch (Exception e) {
                log.error("Rollback transaction error", e);
            }finally {
                if (session != null) {
                    session.close();
                    log.trace("Close session.");
                }
            }
        }finally {
            if (session != null) {
                session.close();
                log.trace("Close session.");
            }
        }
        verifyNodes();
    }

    private static void verifyNodes() throws ConfigException {
        if (typeList.isEmpty()) {
            throw new ConfigException("Modbus types are absence !!!");
        }
    }
}
