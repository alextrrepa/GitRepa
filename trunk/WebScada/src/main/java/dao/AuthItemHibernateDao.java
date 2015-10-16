package dao;

import auth.entities.PermissionEntity;
import auth.entities.UserEntity;
import auth.entities.UserRoleEntity;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import util.SessionUtil;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
            Criteria criteria = session.createCriteria(UserEntity.class);
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

    @Override
    public Set<String> getUserRolesByUsername(String username) {
        Session session = SessionUtil.getSession();
        Transaction transaction = null;
        Set<String> userRoles = null;
        try {
            Criteria criteria = session.createCriteria(UserRoleEntity.class);
            criteria.add(Restrictions.eq("username", username));
            List<UserRoleEntity> listEntity = criteria.list();
            userRoles = new HashSet<>();
            for (UserRoleEntity role : listEntity) {
                userRoles.add(role.getRolename());
            }
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
        return userRoles;
    }

    @Override
    public Set<String> getPermissionsByUsername(String username) {
        Session session = SessionUtil.getSession();
        Transaction transaction = null;
        Set<String> permissions = null;
        try {
            Criteria criteria = session.createCriteria(PermissionEntity.class);
            criteria.add(Restrictions.eq("username", username));
            List<PermissionEntity> permList = criteria.list();
            permissions = new HashSet<>();
            for (PermissionEntity perm : permList) {
                permissions.add(perm.getPermissions());
            }
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
        return permissions;
    }
}