package auth.controllers;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthenticationFilter implements Filter {
    private final static Logger log = Logger.getLogger(AuthenticationFilter.class);
    private FilterConfig filterConfig = null;

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

//        log.info("Session:::" + request.getSession().getAttribute("username"));
        HttpSession session = request.getSession(false);
        String uri = request.getRequestURI();
        log.info("URI:::" + uri);
        log.info("Servlet Path :::" + request.getServletPath());
        log.info("Session:::" + session);
        log.info("ContextPath:::" + request.getContextPath());
        log.info("PathInfo:::" + request.getPathInfo());
        if (session == null) {
            resp.setContentType("text/html");
//            response.sendRedirect(/*request.getContextPath() + */"login.jsp");
//            response.sendRedirect(request.getServletPath());
            String loginPage = filterConfig.getInitParameter("login_page");
            filterConfig.getServletContext().getRequestDispatcher(loginPage).forward(req, resp);
            return;
        } else {
            chain.doFilter(req, resp);
            return;
        }
    }

    public void init(FilterConfig config) throws ServletException {
        this.filterConfig = config;
//        url = config.getInitParameter("login_page");
//        appName = config.getInitParameter("appName");
//        log.info(url);
//        log.info(subjectKey);
    }
}
