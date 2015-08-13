package util;

import dao.GenericDao;
import dao.ItemDAOHibernate;
import modbusserver.exception.ConfigException;
import model.*;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;

import java.util.List;

public class CheckConfigUtil {
    private final static Logger log = Logger.getLogger(CheckConfigUtil.class);

    public static void check() throws ConfigException {
        Class[] mas = {NodeEntity.class, RtuEntity.class, TcpEntity.class, DeviceEntity.class, TagEntity.class};
        for (Class t : mas) {
            long count = getCount(t);
            if (count == 0) {
                throw new ConfigException("Error in modbus configuration!" + " "
                        + t.getSimpleName() + " " + "is empty!");
            }
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
            }catch (Exception ex) {
                log.trace("Can't rollback");
            } finally {
                session.close();
            }
        }
        return counts;
    }

    private static void checkDeviceExistence() {
        boolean flag = true;
        GenericDao config = new ItemDAOHibernate(NodeEntity.class);
        List<NodeEntity> listConfig = config.getAllConfig();
        for (NodeEntity node : listConfig) {

        }

    }
}
