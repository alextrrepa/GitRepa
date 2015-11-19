package admin.controllers.admin_delegation;

import auth.entities.UserEntity;
import dao.AuthDaoIF;
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

    public void getUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AuthDaoIF<UserEntity, Long> userDao = new AuthItemHibernateDao<>(UserEntity.class);
        Long id = Long.valueOf(request.getParameter("userId"));
        UserEntity user = userDao.getById(id);
        request.setAttribute("user", user);
        request.getRequestDispatcher("admin/edituser.jsp").forward(request, response);
    }

    public void updateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.valueOf(request.getParameter("userId"));
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String username = request.getParameter("username");
        Boolean locked = Boolean.parseBoolean(request.getParameter("locked"));
        String description = request.getParameter("description");

        UserEntity user = new UserEntity();
        user.setId(id);
        user.setName(name);
        user.setSurname(surname);
        user.setUsername(username);
        user.setLocked(locked);
        user.setDescription(description);

        CommonOperationsHibernateDao<UserEntity, Long> updateDao = new AuthItemHibernateDao<>(UserEntity.class);
        updateDao.update(user);
        request.getRequestDispatcher("admin?action=accounts").forward(request, response);
    }
}