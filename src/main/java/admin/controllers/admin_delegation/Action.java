package admin.controllers.admin_delegation;

import auth.entities.UserEntity;
import dao.AuthItemHibernateDao;
import dao.CommonOperationsHibernateDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class Action {
    public void getAllAccounts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CommonOperationsHibernateDao<UserEntity, Long> userDao = new AuthItemHibernateDao<>(UserEntity.class);
        List<UserEntity> users = userDao.getAll();
        request.setAttribute("users", users);
        request.getRequestDispatcher("admin/users.jsp").forward(request, response);
    }
}