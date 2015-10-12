package auth.controllers;

import auth.entities.User;
import dao.AuthDaoIF;
import dao.AuthItemHibernateDao;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    private final static Logger log = Logger.getLogger(LoginServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
//        String passsword = request.getParameter("password");
        /*String remoteUser = httpRequest.getRemoteUser();
        log.info("RemoteUser" + remoteUser);
        if (remoteUser != null) {
            Subject subject = (Subject) httpRequest.getSession().getAttribute("subjectkey");
        }*/

        AuthDaoIF<User, Long> authDao = new AuthItemHibernateDao<>(User.class);
        User user = authDao.getUserByUsername(username);
        String name = user.getUsername();
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(2 * 60);
        session.setAttribute("username", name);
        log.info(user.getUsername());
        log.info(user.getPassword());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}