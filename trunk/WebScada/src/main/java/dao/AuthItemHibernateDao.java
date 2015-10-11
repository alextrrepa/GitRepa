package dao;

import auth.entities.User;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import util.SessionUtil;

import java.io.Serializable;

public class AuthItemHibernateDao<T, ID extends Serializable> extends CommonOperationsHibernateDao<T, ID>
        implements AuthDaoIF<T, ID> {
    private final static Logger log = Logger.getLogger(AuthItemHibernateDao.class);

    public AuthItemHibernateDao(Class<T> persistenceClass) {
        super(persistenceClass);
    }

    @Override
    public T getUserByUsername(String login) {
        Session session = SessionUtil.getSession();
        Transaction transaction = null;
        T entity = null;
        try {
            Criteria criteria = session.createCriteria(User.class);
            criteria.add(Restrictions.eq("username", login));
            entity = (T) criteria.uniqueResult();
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
        return entity;
    }
}