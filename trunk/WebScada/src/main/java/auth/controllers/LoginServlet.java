package auth.controllers;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    private final static Logger log = Logger.getLogger(LoginServlet.class);

    public LoginServlet() {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory();
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("user");
        String pass = request.getParameter("password");
        log.info(username);
        log.info(pass);
        if (username == null || pass == null) {
            request.setAttribute("message", "Wrong parameters");
        } else {
            boolean b = tryLogin(username, pass);
            if (b) {
                request.setAttribute("message", "Login successful. Welcome. Open <a href='hello'>hello Servlet</a> to check if you are logged in.");
            } else {
                request.setAttribute("message", "wrong user/pwd or an error...");
            }
        }
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public boolean tryLogin(String username, String password) {
        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            try {
                currentUser.login(token);
                currentUser.getSession().setAttribute("username", username);
                return true;
            } catch (UnknownAccountException uae) {
                System.out.println("There is no user with username of "
                        + token.getPrincipal());
            } catch (IncorrectCredentialsException ice) {
                System.out.println("Password for account "
                        + token.getPrincipal()
                        + " was incorrect!");
            } catch (LockedAccountException lae) {
                System.out.println("The account for username "
                        + token.getPrincipal()
                        + " is locked.  "
                        + "Please contact your administrator to unlock it.");
            }
        } else {
            return true;
        }
        return false;
    }
}
