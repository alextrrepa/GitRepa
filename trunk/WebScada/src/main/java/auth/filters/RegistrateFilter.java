package auth.filters;

import auth.entities.RoleEntity;
import dao.AuthDaoIF;
import dao.AuthItemHibernateDao;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public class RegistrateFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        AuthDaoIF<RoleEntity, Long> roleDao = new AuthItemHibernateDao<>(RoleEntity.class);
        List<RoleEntity> rList = roleDao.getAllRoles();
        request.setAttribute("roles", rList);
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
