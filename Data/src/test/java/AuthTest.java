import org.junit.Test;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

public class AuthTest {

    @Test
    public void testLogin() {
        LoginContext loginContext = null;
        try {
            loginContext = new LoginContext("MyExample");
            loginContext.login();
        } catch (LoginException e) {
            e.printStackTrace();
        }
        Subject sub = loginContext.getSubject();
    }
}
