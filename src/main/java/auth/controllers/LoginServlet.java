package auth.controllers;

import auth.DbUsernameCredential;
import auth.WebCallbackHandler;
import org.apache.log4j.Logger;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

public class LoginServlet extends HttpServlet {
    private final static Logger log = Logger.getLogger(LoginServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String appName = "WebScada";
        LoginContext context = null;
        boolean loginSuccess = true;
        try {
            context = new LoginContext(appName, new WebCallbackHandler(request));
            context.login();
        } catch (LoginException e) {
            e.printStackTrace();
            loginSuccess = false;
        }
        if (loginSuccess) {
            Subject subject = context.getSubject();
            Set credentials = subject.getPublicCredentials(DbUsernameCredential.class);
            log.info("Subject username:::" + credentials.iterator().next());
        } else {
            log.info("Did not authenticate !!!");
        }


//        String username = request.getParameter("username");
//        String passsword = request.getParameter("password");
        /*String remoteUser = httpRequest.getRemoteUser();
        log.info("RemoteUser" + remoteUser);
        if (remoteUser != null) {
            Subject subject = (Subject) httpRequest.getSession().getAttribute("subjectkey");
        }*/

/*
        AuthDaoIF<UserEntity, Long> authDao = new AuthItemHibernateDao<>(UserEntity.class);
        UserEntity user = authDao.getUserByUsername(username);
        String name = user.getUsername();
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(2 * 60);
        session.setAttribute("username", name);
        log.info(user.getUsername());
        log.info(user.getPassword());
*/
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}