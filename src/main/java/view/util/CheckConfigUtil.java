package view.util;

import dao.CommonOperationsHibernateDao;
import dao.ViewItemHibernateDao;
import entities.*;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import util.SessionUtil;
import view.modbusserver.exception.ConfigException;
import view.modbusserver.exception.NullConfigParamsException;
import view.modbusserver.exception.NullDeviceException;
import view.modbusserver.exception.NullTagException;

import java.util.List;

public class CheckConfigUtil {
    private final static Logger log = Logger.getLogger(CheckConfigUtil.class);
    private static CommonOperationsHibernateDao<NodeEntity, Long> config = new ViewItemHibernateDao<>(NodeEntity.class);

    public static void check() throws ConfigException {
        checkTablesConfiguration();
        checkDeviceExistence();
        checkTagExistence();
    }

    private static void checkTablesConfiguration() throws NullConfigParamsException {
        Class[] mas = {NodeEntity.class, RtuEntity.class, TcpEntity.class, DeviceEntity.class, TagEntity.class};
        for (Class t : mas) {
            long count = getCount(t);
/*
            if (count == 0) {
                throw new NullConfigParamsException("Error in modbus configuration!" + " "
                        + t.getSimpleName() + " " + "is empty!");
            }
*/
        }
    }

    private static long getCount(Class cl) {
        Session session = SessionUtil.getSession();
        Transaction transaction = null;
        Long counts = null;
        try {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(cl);
            criteria.setProjection(Projections.rowCount());
            counts = (Long) criteria.uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                transaction.rollback();
            } catch (Exception ex) {
                log.trace("Can't rollback");
            } finally {
                session.close();
            }
        }
        return counts;
    }

    private static void checkDeviceExistence() throws NullDeviceException {
//        GenericDao config = new ItemDAOHibernate(NodeEntity.class);
        List<NodeEntity> listConfig = config.getAll();
        for (NodeEntity node : listConfig) {
            List<DeviceEntity> dev = node.getDeviceEntity();
            if (dev.size() == 0) {
                throw new NullDeviceException("Error in modbus configuration! " + " "
                        + node.getName() + " does not contain devices");
            }
        }
    }

    private static void checkTagExistence() throws NullTagException {
//        GenericDao config = new ItemDAOHibernate(NodeEntity.class);
//        CommonOperationsHibernateDao<NodeEntity, Long> config = new ViewItemHibernateDao<>(NodeEntity.class);
        List<NodeEntity> listConfig = config.getAll();
        for (NodeEntity node : listConfig) {
            List<DeviceEntity> dev = node.getDeviceEntity();
            for (DeviceEntity device : dev) {
                List<TagEntity> tag = device.getTagEntities();
                if (tag.size() == 0) {
                    throw new NullTagException("Error in modbus configuration! " + " "
                            + device.getName() + " does not contain tags");
                }
            }
        }
    }
}
