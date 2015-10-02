package auth;

import org.apache.shiro.authc.SaltedAuthenticationInfo;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;

public class MySaltedAuthentificationInfo implements SaltedAuthenticationInfo {
    private final String username;
    private final String password;
    private final String salt;

    public MySaltedAuthentificationInfo(String username, String password, String salt) {
        this.username = username;
        this.password = password;
        this.salt = salt;
    }

    @Override
    public ByteSource getCredentialsSalt() {
        return new SimpleByteSource(Base64.decode(salt));
    }

    @Override
    public PrincipalCollection getPrincipals() {
        PrincipalCollection collection = new SimplePrincipalCollection(username, username);
        return collection;
    }

    @Override
    public Object getCredentials() {
        return password;
    }
}