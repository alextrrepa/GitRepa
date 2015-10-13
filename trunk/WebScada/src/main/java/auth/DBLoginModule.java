package auth;

import auth.entities.UserEntity;
import dao.AuthDaoIF;
import dao.AuthItemHibernateDao;

import javax.security.auth.Subject;
import javax.security.auth.callback.*;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

public class DBLoginModule implements LoginModule {
    private Subject subject;
    private CallbackHandler callbackHandler;
    private Map sharedState = Collections.EMPTY_MAP;
    private Map options = Collections.EMPTY_MAP;
    private Set principalsAdded;
    private boolean authenticated;
    private String username;
    private Long id;
    private String password;

    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState, Map<String, ?> options) {
        this.subject = subject;
        this.callbackHandler = callbackHandler;
        this.sharedState = sharedState;
        this.options = options;
    }

    @Override
    public boolean login() throws LoginException {
        NameCallback nameCallback = new NameCallback("Username");
        PasswordCallback passwordCallback = new PasswordCallback("Password", false);
        Callback[] callbacks = new Callback[]{nameCallback, passwordCallback};
        try {
            callbackHandler.handle(callbacks);
        } catch (UnsupportedCallbackException e) {
            String className = e.getCallback().getClass().getName();
            throw new LoginException(className + "is not a supported Callback");
        } catch (IOException e) {
            throw new LoginException("IOException logging in.");
        }

        username = nameCallback.getName();
        password = String.valueOf(passwordCallback.getPassword());

        AuthDaoIF<UserEntity, Long> userDao = new AuthItemHibernateDao<>(UserEntity.class);
        UserEntity user = userDao.getUserByUsername(username);
        id = user.getId();
//        String storedUsername = user.getUsername();
        String storedPassword = user.getPassword();
        if (storedPassword != null && storedPassword.equals(password)) {
            authenticated = true;
        } else {
            authenticated = false;
        }
        return authenticated;
    }

    @Override
    public boolean commit() throws LoginException {
        if (!authenticated) {
            return false;
        }
        DbUsernameCredential credential = new DbUsernameCredential(username, id);
        subject.getPublicCredentials().add(credential);
        return false;
    }

    @Override
    public boolean abort() throws LoginException {
        username = null;
        password = null;
        authenticated = false;
        return true;
    }

    @Override
    public boolean logout() throws LoginException {
        return true;
    }
}
