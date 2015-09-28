package auth.controllers;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by Администратор on 28.09.2015.
 */
public class AuthenticationFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
