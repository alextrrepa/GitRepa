package auth;

import javax.security.auth.callback.*;
import javax.servlet.ServletRequest;
import java.io.IOException;

public class WebCallbackHandler implements CallbackHandler {
    private String username;
    private String password;

    public WebCallbackHandler(ServletRequest request) {
        this.username = request.getParameter("username");
        this.password = request.getParameter("password");
    }

    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        for (int i = 0; i < callbacks.length; i++) {
            if (callbacks[i] instanceof NameCallback) {
                NameCallback nameCallback = (NameCallback) callbacks[i];
                nameCallback.setName(username);
            } else if (callbacks[i] instanceof PasswordCallback) {
                PasswordCallback passwordCallback = (PasswordCallback) callbacks[i];
                passwordCallback.setPassword(password.toCharArray());
            } else {
                throw new UnsupportedCallbackException(callbacks[i], "The CallBacks are unrecognized");
            }
        }
    }
}
