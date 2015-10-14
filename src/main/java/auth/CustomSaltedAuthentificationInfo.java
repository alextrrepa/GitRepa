package auth;

import org.apache.shiro.authc.SaltedAuthenticationInfo;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;

public class CustomSaltedAuthentificationInfo implements SaltedAuthenticationInfo {
    private final String username;
    private final String password;
    private final String salt;

    public CustomSaltedAuthentificationInfo(String username, String password, String salt) {
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
        PrincipalCollection prCollection = new SimplePrincipalCollection(username, username);
        return prCollection;
    }

    @Override
    public Object getCredentials() {
        return password;
    }
}
