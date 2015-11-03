package auth.controllers;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
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
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        boolean b = tryLogin(username, password);
        if (b) {
            request.getRequestDispatcher("index.jsp").forward(request, response);
            log.info("Authentication success !!!");
        } else {
            request.setAttribute("error", "Ошибка аутентификации! Неверный логин или пароль");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            log.error("Wrong parameteres");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    private boolean tryLogin(String username, String password) {
        org.apache.shiro.subject.Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            try {
                currentUser.login(token);
                log.info("Is authenticated:::" + currentUser.isAuthenticated());
//                currentUser.checkPermission("menu:view");
//                log.info("Is permitted" + currentUser.isPermitted("menu:view"));
                return true;
            } catch (UnknownAccountException e) {
                log.error("There is no user with username of" + token.getPrincipal());
            } catch (IncorrectCredentialsException ex) {
                log.error("Password for " + token.getCredentials() + "was incorrect!");
            } catch (LockedAccountException ex1) {
                log.error("The account for username" + token.getPrincipal() + "is locked");
            }
        } else {
            return true;
        }
        return false;
    }
}