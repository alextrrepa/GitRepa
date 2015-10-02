package auth;

import auth.dao.UserDao;
import auth.entities.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.realm.jdbc.JdbcRealm;

public class CustomRealm extends JdbcRealm {
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String username = usernamePasswordToken.getUsername();
        if (username == null) {
            System.out.println("Username is null");
            return null;
        }

        UserDao userDao = new UserDao();
        User user = userDao.getUser(username);
        if (user == null) {
            System.out.println("No account found for user [" + username + "]");
            return null;
        }

        SaltedAuthenticationInfo info = new MySaltedAuthentificationInfo(username, user.getPassword(), user.getSalt());
        return info;
//        return super.doGetAuthenticationInfo(token);
    }
}
