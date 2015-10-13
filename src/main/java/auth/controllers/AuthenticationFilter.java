package auth.controllers;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthenticationFilter implements Filter {
    private final static Logger log = Logger.getLogger(AuthenticationFilter.class);

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

//        log.info("Session:::" + request.getSession().getAttribute("username"));
        HttpSession session = request.getSession(false);
//        String uri = request.getRequestURI();
//        log.info("URI:::" + uri);
//        log.info("Servlet Path :::" + request.getServletPath());
        log.info("Session:::" + session);
//        log.info("ContextPath:::" + request.getContextPath());
//        log.info("PathInfo:::" + request.getPathInfo());
        if (session == null) {
//            resp.setContentType("text/html");
//            response.sendRedirect(/*request.getContextPath() + */"login.jsp");
//            response.sendRedirect("login.jsp");
//            String loginPage = filterConfig.getInitParameter("login_page");
//            filterConfig.getServletContext().getRequestDispatcher(loginPage).forward(req, resp);
            RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
            rd.forward(request, response);
            return;
        } else {
            chain.doFilter(req, resp);
            return;
        }
    }

    public void init(FilterConfig config) throws ServletException {
    }
}
