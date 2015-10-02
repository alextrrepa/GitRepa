package auth.dao;

import auth.entities.User;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import util.SessionUtil;

public class UserDao {
    private final static Logger log = Logger.getLogger(UserDao.class);

    public void create(Object object) {
        Session session = SessionUtil.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(object);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                transaction.rollback();
            } catch (Exception ex) {
                log.error("Rollback transaction error", ex);
            }
        } finally {
            session.close();
        }
    }

    public User getUser(String login) {
        Session session = SessionUtil.getSession();
        Transaction transaction = null;
        try {
            Criteria criteria = session.createCriteria(User.class);
            criteria.add(Restrictions.eq("username", login));
            User user = (User) criteria.uniqueResult();
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            try {
                transaction.rollback();
            } catch (Exception ex) {
                log.error("Rollback transaction error", ex);
            }
        }
        return null;
    }
}
