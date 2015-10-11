package dao;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.SessionUtil;

import java.io.Serializable;
import java.util.List;

public class ViewItemHibernateDao<T, ID extends Serializable> extends CommonOperationsHibernateDao<T, ID>
        implements ViewDaoIF<T, ID> {
    private final static Logger log = Logger.getLogger(ViewItemHibernateDao.class);

    public ViewItemHibernateDao(Class<T> persistenceClass) {
        super(persistenceClass);
    }

    @Override
    public List<T> getTags() {
        Session session = SessionUtil.getSession();
        Transaction transaction = null;
        List<T> tags = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from TagEntity");
            tags = query.list();
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
        }
        return tags;
    }
}
