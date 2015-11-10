package dao;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.SessionUtil;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class DataItemHibernateDao<T, ID extends Serializable> extends CommonOperationsHibernateDao<T, ID>
        implements DataDaoIF<T, ID> {
    private final static Logger log = Logger.getLogger(DataItemHibernateDao.class);

    public DataItemHibernateDao(Class<T> persistenceClass) {
        super(persistenceClass);
    }

    @Override
    public List<T> getDataBetweenDates(Date startDate, Date endDate) {
        Session session = SessionUtil.getSession();
        Transaction transaction = null;
        List<T> results = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from TagData tag inner join fetch tag.hourEntities as hours where hours.dtime between :stDate and :endDate");
            query.setParameter("stDate", startDate);
            query.setParameter("endDate", endDate);

            results = query.list();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                try {
                    transaction.rollback();
                } catch (Exception ex) {
                    log.error("Rollback transaction error", ex);
                }
            }
            log.error("Original error when executing query", e);
        } finally {
            session.close();
        }
        return results;
    }
}