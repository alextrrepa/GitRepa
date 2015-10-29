package dao;

import auth.entities.ResourceEntity;
import auth.entities.RoleEntity;
import auth.entities.UrlFilterEntity;
import auth.entities.UserEntity;
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
    public UserEntity getUserByUsername(String login) {
        Session session = SessionUtil.getSession();
        Transaction transaction = null;
        UserEntity entity = null;
        try {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(UserEntity.class);
            criteria.add(Restrictions.eq("username", login));
//            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            entity = (UserEntity) criteria.uniqueResult();
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
        return entity;
    }

    @Override
    public Set<String> getPermissionsByUsername(String username) {
        Session session = SessionUtil.getSession();
        Transaction transaction = null;
        Set<String> permissions = null;
        try {
            transaction = session.beginTransaction();
            UserEntity userEntity = getUserByUsername(username);
//            log.info("UserName:::" + userEntity.getUsername());
            Set<RoleEntity> sUsers = userEntity.getRoles();
            permissions = new HashSet<>();
            for (RoleEntity r : sUsers) {
//                log.info("UserRole:::" + r.getRole());
                Set<ResourceEntity> sResources = r.getResources();
                for (ResourceEntity res : sResources) {
//                    log.info(res.getPermission());
                    permissions.add(res.getPermission());
                }
            }
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
        return permissions;
    }

    @Override
    public Set<String> getRolesByUsername(String username) {
        Session session = SessionUtil.getSession();
        Transaction transaction = null;
        Set<String> roles = null;
        try {
            transaction = session.beginTransaction();
            UserEntity user = getUserByUsername(username);
            roles = new HashSet<>();
            Set<RoleEntity> roleEntities = user.getRoles();
            for (RoleEntity r : roleEntities) {
                roles.add(r.getRole());
            }
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
        return roles;
    }

    @Override
    public List<RoleEntity> getAllRoles() {
        Session session = SessionUtil.getSession();
        Transaction transaction = null;
        List<RoleEntity> roles = null;
        try {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(RoleEntity.class);
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            roles = criteria.list();
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
        return roles;
    }

    @Override
    public List<UrlFilterEntity> getUrlFilters() {
        Session session = SessionUtil.getSession();
        Transaction transaction = null;
        List<UrlFilterEntity> urlFilters = null;
        try {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(UrlFilterEntity.class);
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            urlFilters = criteria.list();
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
        return urlFilters;
    }
}