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
    public List<T> getHourDataBetweenDates(Date startDate, Date endDate) {
        Session session = SessionUtil.getSession();
        Transaction transaction = null;
        List<T> results = null;
        try {
            transaction = session.beginTransaction();
//            Criteria criteria = session.createCriteria(TagData.class)
//                    .createCriteria("hourEntities", "hr")
//                    .add(Restrictions.between("hr.dtime", startDate, endDate));
//            Criteria subquery = criteria.createCriteria("hourEntities", "hs");
//            subquery.add(Restrictions.between("hs.dtime", startDate, endDate));
//            criteria.createAlias("hourEntities", "hs");
//            criteria.add(Restrictions.ge("hs.dtime", startDate));
//            criteria.add(Restrictions.le("hs.dtime", endDate));
//            criteria
//            .add(Restrictions.between("hs.dtime", startDate, endDate));
//            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
//            results = criteria.list();
            Query query = session.createQuery("select distinct tag from TagData tag " +
                    "inner join fetch tag.hourEntities h where h.dtime between :stDate and :endDate order by h.dtime");
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