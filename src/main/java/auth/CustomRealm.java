package auth;

import auth.entities.UserEntity;
import dao.AuthDaoIF;
import dao.AuthItemHibernateDao;
import org.apache.log4j.Logger;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class CustomRealm extends AuthorizingRealm {
    private final static Logger log = Logger.getLogger(CustomRealm.class);

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) principalCollection.getPrimaryPrincipal();
//        String username = (String) getAvailablePrincipal(principalCollection);
        log.info("Authorization !!!!!");
        log.info("AuthorizationInfo:::" + username);

        AuthDaoIF<String, Long> rolesDao = new AuthItemHibernateDao<>(String.class);
//        Set<String> roleNames = rolesDao.getUserRolesByUsername(username);
//        Set<String> permissions = rolesDao.getPermissionsByUsername(username);

//        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roleNames);
//        info.setStringPermissions(permissions);
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken userToken = (UsernamePasswordToken) authenticationToken;
        final String username = userToken.getUsername();
        log.info("AuthenticationInfo:::" + username);
        if (username == null) {
            log.error("Username is null!");
            return null;
        }
        AuthDaoIF<UserEntity, Long> authDao = new AuthItemHibernateDao<>(UserEntity.class);
        UserEntity userEntity = authDao.getUserByUsername(username);
        if (userEntity == null) {
            throw new AuthenticationException("There is no username");
        }
        SaltedAuthenticationInfo info = new CustomSaltedAuthentificationInfo(
                username, userEntity.getPassword(), userEntity.getSalt());
        return info;
    }
}