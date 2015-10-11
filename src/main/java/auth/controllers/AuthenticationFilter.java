package auth.controllers;

import auth.entities.User;
import dao.AuthDaoIF;
import dao.AuthItemHibernateDao;
import org.apache.log4j.Logger;

import javax.security.auth.Subject;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class AuthenticationFilter implements Filter {
    private final static Logger log = Logger.getLogger(AuthenticationFilter.class);
    private String subjectKey;
    private String appName;


    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) req;
        String remoteUser = httpRequest.getRemoteUser();
        log.info("RemoteUser" + remoteUser);
        if (remoteUser != null) {
            Subject subject = (Subject) httpRequest.getSession().getAttribute("subjectkey");
        }

        String password = null;
        AuthDaoIF<User, Long> authDao = new AuthItemHibernateDao<>(User.class);
        User user = authDao.getUserByUsername(remoteUser);
        log.info(user.getUsername());
        log.info(user.getPassword());
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
        subjectKey = config.getInitParameter("subjectkey");
        appName = config.getInitParameter("appName");
        log.info(appName);
        log.info(subjectKey);
    }

}
