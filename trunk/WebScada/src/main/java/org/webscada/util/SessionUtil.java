package org.webscada.util;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class SessionUtil {
    private final static Logger log = Logger.getLogger(SessionUtil.class);
    private final static SessionUtil instance = new SessionUtil();
    private SessionFactory factory;

    private SessionUtil() {
        log.trace("Opening session....");
        Configuration configuration = new Configuration().configure();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().
        applySettings(configuration.getProperties());
        factory = configuration.buildSessionFactory(builder.build());
    }

    public static Session getSession() throws HibernateException {
        log.trace("Session Open !");
        return getInstance().factory.openSession();
    }

    private static SessionUtil getInstance(){
        return instance;
    }
}
